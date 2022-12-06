(ns problems.problem-06
  (:require [common.helpers :refer [input]]
            [clojure.string :as str]))

(defn get-arr [arr value]
  (if (= 3 (count arr))
    (conj arr value)
    (conj (vec (drop 1 arr)) value)))

(defn packet-index [s]
  (let [split (str/split s #"")
        r (range 1 (inc (count split)))
        combined (map vector split r)]
    ((reduce (fn [{:keys [arr result] :as d} value]
               (if (not= result nil)
                 d
                 (let [arr (get-arr arr (first value))]
                   (if (= 4 (count (set arr)))
                     (assoc d :result (second value))
                     (assoc d :arr arr))))) {:arr (vec (map first (take 3 combined))) :result nil} (drop 3 combined)) :result)))

(defn answer-a [file]
  (->> file
       input
       first
       packet-index))

(answer-a "data/problem-06-a.txt")

(defn answer-b [file] "Not implemented yet")

(defn answer []
  (println "06: A:" (answer-a "data/problem-06-input.txt"))
  (println "06: B:" (answer-b "data/problem-06-input.txt")))