(ns problems.problem-18
  (:require [clojure.string :as str]
            [common.helpers :refer [debug input]]
            [clojure.pprint :refer [pprint]]))

(def lava {:points {}})

(defn parse-input
  [file]
  (->> file
       input
       (map #(str/split % #","))
       (map (fn [s]
              (map read-string s)))))

(defn adjacent-points
  [[x y z]]
  (map
   (fn [[x1 y2 z2]]
     [(+ x x1) (+ y y2) (+ z z2)])
   [[1 0 0],[-1 0 0],[0 1 0],[0 -1 0],[0 0 1],[0 0 -1]]))

(defn reduce-visibility
  [lava point adjacent]
  (reduce (fn [lava ap]
            (if (get-in lava [:points ap])
              (-> lava
                  (update-in [:points ap :visibility] dec)
                  (update-in [:points point :visibility] dec))
              lava)) lava adjacent))

(defn build-lava
  [lava input]
  (reduce
   (fn [lava cube]
     (let [lava (assoc-in lava [:points cube] {:visibility 6})
           adjacent-points (adjacent-points cube)]
       (reduce-visibility lava cube adjacent-points)))
   lava
   input))

(defn answer-a
  [file]
  (let [lava-graph (->> file
                        parse-input
                        (build-lava lava))]
    ;; (pprint lava-graph)
    (reduce + (map #((second %) :visibility) (lava-graph :points)))))

(defn answer-b
  [file]
  "Not implemented yet")

(defn answer []
  (println "18: A:" (answer-a "data/problem-18-input.txt"))
  (println "18: B:" (answer-b "data/problem-18-input.txt")))