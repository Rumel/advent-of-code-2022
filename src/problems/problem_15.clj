(ns problems.problem-15
  (:require [common.helpers :refer [input]]))

(def data
  {:sensors {}
   :beacons #{}
   :low-x ##Inf
   :high-x ##-Inf
   :low-y ##Inf
   :high-y ##-Inf})

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

(defn create-sensor
  [x y distance]
  {:x x
   :y y
   :distance distance
   :low-x (- x distance)
   :high-x (+ x distance)
   :low-y (- y distance)
   :high-y (+ y distance)})

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
            (-> data
                (update :beacons conj [b-x b-y])
                (assoc-in [:sensors [s-x s-y]] (create-sensor s-x s-y distance)))))
        data)))

(defn filter-sensor
  [sensor line-num]
  (and (<= line-num (sensor :high-y))
       (>= line-num (sensor :low-y))))

(defn no-beacon-range
  [sensor line-num]
  (let [x (sensor :x)
        s-y (sensor :y)
        cur-dist (abs (- line-num s-y))
        distance (sensor :distance)
        diff (- distance cur-dist)
        low-x (- x diff)
        high-x (+ x diff)]
    [low-x high-x]))

(defn merge-ranges
  [ranges]
  (let [sorted (sort-by first ranges)
        ranges ((reduce
                 (fn
                   [{:keys [ranges] :as d} r]
                   (let [l (first ranges)
                         [x1 x2] l
                         [x3 x4] r]
                     (if (nil? l)
                       (assoc d :ranges (conj ranges r))
                       (cond
                         (and (<= x3 x2) (> x4 x2))
                         (assoc d :ranges (conj (drop 1 ranges) [x1 x4]))

                         (> x3 x2)
                         (assoc d :ranges (conj ranges r))

                         :else
                         d))))
                 {:ranges []}
                 sorted) :ranges)]
    ranges))

(defn no-beacons
  [sensors beacons line-num]
  (let [ranges (map #(no-beacon-range % line-num) sensors)
        ;; ranges (map #(set (range (first %) (second %))) ranges)
        ;; ranges (reduce clojure.set/union ranges)
        ranges (merge-ranges ranges)
        c (reduce
           (fn
             [acc [x1 x2]]
             (+ acc (- x2 x1)))
           0
           ranges)]
    c))

(defn answer-a
  [file line-num]
  (let [data (->> file
                  parse-input)
        sensors (filter #(filter-sensor % line-num) (vals (data :sensors)))
        beacons (data :beacons)]
    (no-beacons sensors beacons line-num)))

(defn answer-b
  [file]
  "Not implemented yet")

(defn answer []
  ;; "Elapsed time: 27224.432958 msecs"

  ;; Created beacons right away
  ;; Memory still shoots up past 3 gigs
  ;;"Elapsed time: 18790.824125 msecs"
  ;;"Elapsed time: 18587.145542 msecs"

  ;; Moved to ranges
  ;; Memory around 1.6 gigs
  ;; "Elapsed time: 5437.54025 msecs"
  ;; "Elapsed time: 5054.881834 msecs"

  ;; Moved to vector ranges and counting
  ;; "Elapsed time: 6.738292 msecs"
  ;; "Elapsed time: 4.8735 msecs"
  ;; "Elapsed time: 7.111083 msecs"
  (time
   (println "15: A:" (answer-a "data/problem-15-input.txt" 2000000)))
  (println "15: B:" (answer-b "data/problem-15-input.txt")))