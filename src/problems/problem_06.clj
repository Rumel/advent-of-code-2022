(ns problems.problem-06
  (:require [common.helpers :refer [input]]
            [clojure.string :as str]))

(defn get-arr [arr value n]
  (if (= (dec n) (count arr))
    (conj arr value)
    (conj (vec (drop 1 arr)) value)))

(defn packet-index [n s]
  (let [split (str/split s #"")
        r (range 1 (inc (count split)))
        combined (map vector split r)]
    ((reduce (fn [{:keys [arr result] :as d} value]
               (if (not= result nil)
                 d
                 (let [arr (get-arr arr (first value) n)]
                   (if (= n (count (set arr)))
                     (assoc d :result (second value))
                     (assoc d :arr arr))))) {:arr (vec (map first (take (dec n) combined))) :result nil} (drop (dec n) combined)) :result)))

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