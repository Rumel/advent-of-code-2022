(ns problems.problem-04
  (:require [clojure.string :as str]))

(defn lines [file] (str/split-lines (slurp file)))

(defn nums [[a b]]
  [(read-string a) (read-string b)])

(defn convert [line]
  (let [split (str/split line #",")
        num-split (map #(str/split % #"-") split)]
    (map nums num-split)))

(defn contained? [[[a b] [c d]]]
  (or (and (>= a c) (<= b d))
      (and (>= c a) (<= d b))))

(defn answer-a [file]
  (->> file
       lines
       (map #(convert %))
       (map #(contained? %))
       (filter true?)
       count))

(defn answer-b [file] "Not implemented yet")

(defn answer []
  (println "04: A:" (answer-a "data/problem-04-input.txt"))
  (println "04: B:" (answer-b "data/problem-04-input.txt")))