(ns problems.problem-09
  (:require [common.helpers :refer [input debug]]
            [clojure.string :as str]))

(defn point
  "Returns a point in the form of {:x 0 :y 0}"
  [x y]
  {:x x :y y})

(defn parsed-input
  [lines]
  (->> lines
       (map (fn [line]
              (let [split (str/split line #" ")]
                {:direction (split 0) :num (read-string (split 1))})))))

(defn move-direction
  "Move the head one space in the direction"
  [{:keys [head head-visited] :as d} direction]
  (let [new-head (case direction
                   "R" (point (inc (head :x)) (head :y))
                   "L" (point (dec (head :x)) (head :y))
                   "U" (point (head :x) (inc (head :y)))
                   "D" (point (head :x) (dec (head :y))))
        new-head-visited (conj head-visited new-head)]
    (assoc d :head new-head :head-visited new-head-visited)))

(defn get-tail-position
  [diff {:keys [x y] :as current-tail}]
  (cond
    (= diff (point 2 0)) (point (inc x) y)
    (= diff (point 0 2)) (point x (inc y))
    (= diff (point -2 0)) (point (dec x) y)
    (= diff (point 0 -2)) (point x (dec y))
    (= diff (point 2 1)) (point (inc x) (inc y))
    (= diff (point 1 2)) (point (inc x) (inc y))
    (= diff (point -2 1)) (point (dec x) (inc y))
    (= diff (point 1 -2)) (point (inc x) (dec y))
    (= diff (point 2 -1)) (point (inc x) (dec y))
    (= diff (point -1 2)) (point (dec x) (inc y))
    (= diff (point -2 -1)) (point (dec x) (dec y))
    (= diff (point -1 -2)) (point (dec x) (dec y))
    :else current-tail))


(defn move-tail
  "Move the tail if necessary"
  [{:keys [head tail tail-visited] :as d}]
  (let [diff-x (- (head :x) (tail :x))
        diff-y (- (head :y) (tail :y))
        diff (point diff-x diff-y)]
    (if (or (= (abs diff-x) 2) (= (abs diff-y) 2))
      (let [new-tail-position (get-tail-position diff tail)]
        (assoc d :tail new-tail-position :tail-visited (conj tail-visited new-tail-position)))
      d)))

(defn debug-instruction
  [{:keys [head tail] :as d}]
  (let [row (vec (take 6 (cycle "-")))
        board (map (fn [x] row) (range 0 6))
        board (into [] board)
        board (assoc-in board [(- 5 (head :y)) (head :x)] "H")
        board (assoc-in board [(- 5 (tail :y)) (tail :x)] "T")
        board (str/join "\n" (map #(str/join " " %) board))]
    (println "New Board")
    (println board)
    (println ""))
  d)

(defn process-instruction
  [{:keys [visited head tail] :as d} {:keys [direction num] :as instruction}]
  (if (= num 0)
    d
    (-> d
        (move-direction direction)
        move-tail
        ;; (debug-instruction)
        (process-instruction {:direction direction :num (dec num)}))))

(defn simulate
  "Simulate all the instructions"
  [instructions]
  (let [base (point 0 0)]
    (reduce
     process-instruction
     {:tail-visited #{base} :head-visited #{base} :head base :tail base}
     instructions)))

(defn answer-a [file]
  (->> file
       input
       parsed-input
       simulate
       :tail-visited
       count))

(answer-a "data/problem-09-a.txt")

(defn answer-b [file] "Not implemented yet")

(defn answer []
  (println "09: A:" (answer-a "data/problem-09-input.txt"))
  (println "09: B:" (answer-b "data/problem-09-input.txt")))