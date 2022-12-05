(ns problems.problem-05-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-05 :refer
             [answer-a
              answer-b
              execute-instruction
              matcher
              move-crate]]))

(deftest matcher-test
  (is (= ["move 1 from 2 to 1" "1" "2" "1"] (re-matches matcher "move 1 from 2 to 1")))
  (is (= ["move 15 from 3 to 9" "15" "3" "9"] (re-matches matcher "move 15 from 3 to 9"))))

(deftest move-crate-test
  (is (= [[1] [3 2] []] (move-crate [[1 2] [3] []] 1 2)))
  (is (= [[1] [3 4 2] []] (move-crate [[1 2] [3 4] []] 1 2))))

(deftest execute-instruction-test
  (is (= [[1] [3 2] []] (execute-instruction [[1 2] [3] []] [1 1 2])))
  (is (= [[] [3 2 1] []] (execute-instruction [[1 2] [3] []] [2 1 2]))))

(deftest answer-a-test
  (is (= "CMZ" (answer-a "data/problem-05-a.txt"))))

(deftest answer-b-test
  (is (= "Not implemented yet" (answer-b "data/problem-05-a.txt"))))