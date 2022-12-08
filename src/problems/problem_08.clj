(ns problems.problem-08
  (:require [common.helpers :refer [input debug]]
            [clojure.string :as str]))

(defn parse-line [line]
  (map read-string (str/split line #"")))

(defn get-left [arr x y]
  (if (= x 0)
    []
    (map #(aget arr y %) (range 0 x))))

(defn get-right [arr x y]
  (let [size (count (aget arr 0))]
    (if (= x (dec size))
      []
      (map #(aget arr y %) (range (inc x) size)))))

(defn get-up [arr x y]
  (if (= y 0)
    []
    (map #(aget arr % x) (range 0 y))))

(defn get-down [arr x y]
  (let [size (count arr)]
    (if (= y (dec size))
      []
      (map #(aget arr % x) (range (inc y) size)))))

(defn is-vible-side [item side]
  (if (empty? side)
    true
    (every? #(> item %) side)))

(defn is-visible [arr x y]
  (let [item (aget arr y x)
        left (get-left arr x y)
        right (get-right arr x y)
        up (get-up arr x y)
        down (get-down arr x y)]
    (or (is-vible-side item left)
        (is-vible-side item right)
        (is-vible-side item up)
        (is-vible-side item down))))

(defn visible-count [arr]
  (let [size (count arr)]
    (for [y (range 0 size)
          x (range 0 size)]
      (if (is-visible arr x y)
        1
        0))))

(defn visible-trees [coll]
  (->> coll
       (reduce (fn [{:keys [v l] :as d} t]
                 (cond
                   (nil? l) {:v (inc v) :l t}
                   (<= l t) {:v (inc v) :l t}
                   :else (reduced d)))
               {:v 0 :l nil})
       :v))

(defn visible-score [arr x y]
  (let [left (reverse (get-left arr x y))
        right (get-right arr x y)
        up (reverse (get-up arr x y))
        down (get-down arr x y)]
    (* (visible-trees left)
       (visible-trees right)
       (visible-trees down)
       (visible-trees up))))

(defn highest-view
  [arr]
  (let [size (count arr)]
    (for [y (range 0 size)
          x (range 0 size)]
      (visible-score arr x y))))

(defn parsed-input [file]
  (->> file
       input
       (map parse-line)))

(defn answer-a [file]
  (->> file
       parsed-input
       to-array-2d
       visible-count
       (reduce + 0)))

(defn answer-b [file]
  (->> file
       parsed-input
       to-array-2d
       highest-view
       ((fn [arr]
          (debug (to-array-2d (partition 5 arr)))
          arr))
       (apply max)))

;; 378 is too low
(defn answer []
  (println "08: A:" (answer-a "data/problem-08-input.txt"))
  (println "08: B:" (answer-b "data/problem-08-input.txt")))