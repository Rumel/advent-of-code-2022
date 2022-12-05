(ns problems.problem-05
  (:require [common.helpers :refer [input]]
            [clojure.string :as str]))

(def matcher #"move (\d+) from (\d+) to (\d+)")

(defn update-crates [crates values]
  (loop [ret-crates []
         fc (first crates)
         rc (rest crates)
         fv (first values)
         rv (rest values)]
    (if (nil? fc)
      ret-crates
      (if (not (= \space fv))
        (recur (conj ret-crates (conj fc fv)) (first rc) (rest rc) (first rv) (rest rv))
        (recur (conj ret-crates fc) (first rc) (rest rc) (first rv) (rest rv))))))

(defn input->crates [input]
  (let [reversed (reverse input)
        parsed (map (fn [line]
                      (take-nth 4 (drop 1 line))) reversed)
        crate-count (count (first parsed))
        crates (take crate-count (cycle [[]]))
        current-stack (drop 1 parsed)]
    (loop [crates crates
           f (first current-stack)
           r (rest current-stack)]
      (if (nil? f)
        crates
        (recur (update-crates crates f) (first r) (rest r))))))

(defn input->instructions [input]
  (map (fn [s]
         (let [[_ num from to] (re-matches matcher s)]
           (map read-string [num from to]))) input))

(defn parse-input [input]
  (let [crates (input->crates (take-while not-empty input))
        instructions (input->instructions (rest (drop-while not-empty input)))]
    [crates instructions]))

(defn move-crate [crates from to]
  (let [crate (peek (crates (dec from)))
        old-stack (pop (crates (dec from)))
        new-stack (conj (crates (dec to)) crate)
        crates' (assoc crates (dec from) old-stack)
        crates'' (assoc crates' (dec to) new-stack)]
    crates''))

(defn execute-instruction-a [crates instruction]
  (let [[times from to] instruction]
    (loop [i times
           crates crates]
      (if (= i 0)
        crates
        (recur (dec i) (move-crate crates from to))))))

(defn execute-instruction-b [crates instruction]
  (let [[times from to] instruction
        items (take-last times (crates (dec from)))
        old-stack (drop-last times (crates (dec from)))
        new-stack (concat (crates (dec to)) items)
        crates' (assoc crates (dec from) old-stack)
        crates'' (assoc crates' (dec to) new-stack)]
    crates''))

(defn answer-a [file]
  (let [[crates instructions] (->> file
                                   input
                                   parse-input)]
    (->> (reduce execute-instruction-a crates instructions)
         (map #(peek %))
         (str/join))))

(defn answer-b [file]
  (let [[crates instructions] (->> file
                                   input
                                   parse-input)]
    (->> (reduce execute-instruction-b crates instructions)
         (map #(last %))
         (str/join))))

(defn answer []
  (println "05: A:" (answer-a "data/problem-05-input.txt"))
  (println "05: B:" (answer-b "data/problem-05-input.txt")))