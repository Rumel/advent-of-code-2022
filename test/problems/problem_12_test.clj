(ns problems.problem-12-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-12
             :refer
             [answer-a
              answer-b
              get-edges]]))

(deftest get-edges-test
  (is (= [[0 1] [1 0]] (get-edges 0 0 5 5)))
  (is (= [[1 2] [1 0] [2 1] [0 1]] (get-edges 1 1 5 5)))
  (is (= [[4 3] [3 4]] (get-edges 4 4 5 5))))

(deftest answer-a-test
  (is (= 31 (answer-a "data/problem-12-a.txt"))))

(deftest answer-b-test
  (is (= 0 (answer-b "Not implemented yet"))))