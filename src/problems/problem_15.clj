(ns problems.problem-15
  (:require [common.helpers :refer [input]]))

(def data
  {:sensors [] :low-x ##Inf :high-x ##-Inf :low-y ##Inf :high-y ##-Inf})

(def regex #"Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)")

(defn manhattan-distance
  [x1 y1 x2 y2]
  (+ (abs (- x2 x1))
     (abs (- y2 y1))))

(defn update-bound
  [data f key value]
  (if (f value (data key))
    (assoc data key value)
    data))

(defn update-bounds
  [data x y distance]
  (-> data
      (update-bound < :low-x (- x distance))
      (update-bound > :high-x (+ x distance))
      (update-bound < :low-y (- y distance))
      (update-bound > :high-y (+ y distance))))

(defn parse-input
  [file]
  (->> file
       input
       (map #(rest (re-matches regex %)))
       (reduce
        (fn
          [data line]
          (let [[a b c d] line
                s-x (read-string a)
                s-y (read-string b)
                b-x (read-string c)
                b-y (read-string d)
                distance (manhattan-distance s-x s-y b-x b-y)
                data (update-bounds data s-x s-y distance)]
            data))
        data)))

(defn answer-a
  [file]
  (->> file
       parse-input))

(println (answer-a "data/problem-15-a.txt"))

(defn answer-b
  [file]
  "Not implemented yet")

(defn answer []
  (println "15: A:" (answer-a "data/problem-15-input.txt"))
  (println "15: B:" (answer-b "data/problem-15-input.txt")))