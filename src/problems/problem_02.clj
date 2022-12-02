(ns problems.problem-02
  (:require [clojure.string :as str]))


(defn read-file
  [file]
  (->> file
       slurp
       str/split-lines
       (map #(str/split % #" "))))

(def throw-score {"X" 1 "Y" 2 "Z" 3})

(def hand-result
  {"A" {"X" 3 "Y" 6 "Z" 0}
   "B" {"X" 0 "Y" 3 "Z" 6}
   "C" {"X" 6 "Y" 0 "Z" 3}})

(defn get-hand-score
  [[h1 h2]]
  (+ (throw-score h2) (get-in hand-result [h1 h2])))

(defn get-score
  [hands]
  (->> hands
       (map get-hand-score)
       (reduce + 0)))

(defn answer-a
  [file]
  (-> file
      read-file
      get-score))

(defn answer-b [file] "Not implemented yet")

(defn answer []
  (println "02: A:" (answer-a "data/problem-02-input.txt"))
  (println "02: B:" (answer-b "data/problem-02-input.txt")))