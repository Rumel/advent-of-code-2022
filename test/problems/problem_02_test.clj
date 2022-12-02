(ns problems.problem-02-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-02 :refer
             [answer-a
              answer-b
              get-hand-score
              get-score]]))

(def hands
  [["A" "Y"]
   ["B" "X"]
   ["C" "Z"]])

(deftest get-hand-score-test
  (is (= 8 (get-hand-score (hands 0))))
  (is (= 1 (get-hand-score (hands 1))))
  (is (= 6 (get-hand-score (hands 2)))))

(deftest get-score-test
  (is (= 15 (get-score hands))))

(deftest answer-a-test
  (is (= (answer-a "data/problem-02-a.txt") 15)))

(deftest answer-b-test
  (is (= (answer-b "data/problem-02-b.txt") "Not implemented yet")))