(ns problems.problem-03
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn lines [file]
  (->> (slurp file)
       (str/split-lines)
       (map #(char-array %))))

(defn duplicate-char [line]
  (let [n (/ (count line) 2)
        a (take n line)
        b (drop n line)]
    (first (set/intersection (set a) (set b)))))

(defn char-value [char]
  (let [value (int char)]
    (if (> value 90)
      (- value 96)
      (- value 38))))

(defn answer-a [file]
  (->> file
       lines
       (map #(duplicate-char %))
       (map #(char-value %))
       (reduce + 0)))

(defn answer-b [file] "Not implemented yet")

(defn answer []
  (println "03: A:" (answer-a "data/problem-03-input.txt"))
  (println "03: B:" (answer-b "data/problem-03-input.txt")))