(ns problems.problem-01
  "Day 1: Calorie Counting
   
   Problem: https://adventofcode.com/2022/day/1"
  (:require [clojure.string :as str]))

(defn get-calorie-totals
  "Take the elves calories and get the total per elf"
  [calories]
  (map #(reduce + %) calories))

(defn max-calories
  "Return the max calories"
  [calories]
  (apply max calories))

(defn convert-to-vec
  "Take a string and convert it to a vector of vector of numbers."
  [s]
  (let [lines (str/split-lines s)]
    (loop [lines lines
           ret-arr []
           cur-arr []]
      (if (empty? lines)
        (conj ret-arr cur-arr)
        (if (empty? (first lines))
          (recur (rest lines) (conj ret-arr cur-arr) [])
          (recur (rest lines) ret-arr (conj cur-arr (read-string (first lines)))))))))

(defn top-three-calories
  "Return a vector of the top 3 calorie counts"
  [calorie-counts]
  (take 3 (sort > calorie-counts)))

(defn answer-a
  "Read the elves' calories and return the max elf's calories"
  [file]
  (-> file
      slurp
      convert-to-vec
      get-calorie-totals
      max-calories))

(defn answer-b
  "Read the elves' calories and return the max elf's calories"
  [file]
  (-> file
      slurp
      convert-to-vec
      get-calorie-totals
      top-three-calories
      (as-> calories (reduce + calories))))

(defn answer []
  (println "01: A:" (answer-a "data/problem-01-input.txt"))
  (println "01: B:" (answer-b "data/problem-01-input.txt")))