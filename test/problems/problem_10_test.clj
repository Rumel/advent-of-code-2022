(ns problems.problem-10-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-10 :refer [answer-a answer-b]]))

(deftest answer-a-test
  (is (=  13140 (answer-a "data/problem-10-a.txt"))))

(deftest answer-b-test
  (is (= 0 (answer-b "data/problem-10-a.txt"))))