(ns problems.problem-01
  "Day 1: Calorie Counting"
  (:require [clojure.string :as str]))

(defn get-calorie-totals [calories]
  (map #(reduce + %) calories))

(defn max-calories [calories]
  (apply max calories))

(defn convert-to-vec [s]
  (let [lines (str/split-lines s)]
    (loop [lines lines
           ret-arr []
           cur-arr []]
      (if (empty? lines)
        (conj ret-arr cur-arr)
        (if (empty? (first lines))
          (recur (rest lines) (conj ret-arr cur-arr) [])
          (recur (rest lines) ret-arr (conj cur-arr (read-string (first lines)))))))))

(defn answer-a [file]
  (-> file
      slurp
      convert-to-vec
      get-calorie-totals
      max-calories))

(defn answer-b [file] "Not implemented yet")

(defn answer []
  (println "01: A:" (answer-a "data/problem-01-input.txt"))
  (println "01: B:" (answer-b "data/problem-01-a.txt")))