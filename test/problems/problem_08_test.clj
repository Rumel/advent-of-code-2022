(ns problems.problem-08-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-08 :refer [answer-a answer-b]]))

(deftest answer-a-test
  (is (= 21 (answer-a "data/problem-08-a.txt"))))

(deftest answer-b-test
  (is (= "Not implemented yet" (answer-b "data/problem-08-a.txt"))))