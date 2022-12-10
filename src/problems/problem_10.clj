(ns problems.problem-10
  (:require [common.helpers :refer [input]]))

(defn parse-line [line]
  (let [noop-match (re-matches #"noop" line)
        addx-match (re-matches #"addx (-?\d+)" line)]
    (cond
      (= false (nil? noop-match)) {:instruction :noop :data nil}
      (= false (nil? addx-match)) {:instruction :addx :data (read-string (addx-match 1))}
      :else {:instruction :fail :data nil})))

(defn parsed-input [file]
  (->> file
       input
       (map parse-line)))

(defn cycle-cpu [d]
  (let [cycle (d :cycle)]
    (if (not (nil? (#{20 60 100 140 180 220} cycle)))
      (let [results (d :results)
            register-x (d :register-x)]
        (assoc d :cycle (inc cycle) :results (conj results (* register-x cycle))))
      (assoc d :cycle (inc cycle)))))

(defn addx [acc n]
  (-> acc
      cycle-cpu
      cycle-cpu
      ((fn [d]
         (let [register-x (acc :register-x)]
           (assoc d :register-x (+ register-x n)))))))

(defn process-instructions [instructions]
  (reduce (fn [acc instruction]
            (cond
              (= :noop (instruction :instruction)) (cycle-cpu acc)
              (= :addx (instruction :instruction)) (addx acc (instruction :data))
              :else acc))
          {:cycle 1 :results [] :register-x 1}
          instructions))

(defn answer-a [file]
  (->> file
       parsed-input
       process-instructions
       :results
       (apply +)))

(defn answer-b [file] "Not implemented yet")

(defn answer []
  (println "10: A:" (answer-a "data/problem-10-input.txt"))
  (println "10: B:" (answer-b "data/problem-10-input.txt")))