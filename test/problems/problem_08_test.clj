(ns problems.problem-08-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-08
             :refer
             [answer-a
              answer-b
              get-down
              get-left
              get-right
              get-up]]))

(def test-array
  (to-array-2d
   [[3, 0, 3, 7, 3],
    [2, 5, 5, 1, 2],
    [6, 5, 3, 3, 2],
    [3, 3, 5, 4, 9],
    [3, 5, 3, 9, 0]]))

(deftest get-left-test
  (is (= [6 5] (get-left test-array 2 2)))
  (is (= [] (get-left test-array 0 0))))
(deftest get-right-test
  (is (= [3 2] (get-right test-array 2 2)))
  (is (= [] (get-right test-array 5 0))))
(deftest get-down-test
  (is (= [5 3] (get-down test-array 2 2)))
  (is (= [] (get-down test-array 0 5))))
(deftest get-up-test
  (is (= [3 5] (get-up test-array 2 2)))
  (is (= [] (get-up test-array 0 0))))

(deftest answer-a-test
  (is (= 21 (answer-a "data/problem-08-a.txt")))
  (is (= 1672 (answer-a "data/problem-08-input.txt"))))

(deftest answer-b-test
  (is (= "Not implemented yet" (answer-b "data/problem-08-a.txt"))))