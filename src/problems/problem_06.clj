(ns problems.problem-06
  (:require [common.helpers :refer [input]]
            [clojure.string :as str]))

(defn packet-index [n s]
  (let [partitions (partition n 1 (str/split s #""))]
    ((reduce (fn [{:keys [index result] :as d} value]
               (if (not= result nil)
                 d
                 (if (= n (count (set value)))
                   (assoc d :result index)
                   (assoc d :index (inc index)))))
             {:index n :result nil}
             partitions)
     :result)))

(defn answer-a [file]
  (->> file
       input
       first
       (packet-index 4)))

(defn answer-b [file]
  (->> file
       input
       first
       (packet-index 14)))

(defn answer []
  (println "06: A:" (answer-a "data/problem-06-input.txt"))
  (println "06: B:" (answer-b "data/problem-06-input.txt")))