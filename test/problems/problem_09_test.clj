(ns problems.problem-09-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-09
             :refer
             [answer-a
              answer-b
              move-direction
              point]]))

(def move-direction-starting {:head (point 1 1) :head-visited #{(point 1 1)}})
(deftest move-direction-test
  (is (= (point 2 1) ((move-direction move-direction-starting "R") :head)))
  (is (= (point 0 1) ((move-direction move-direction-starting "L") :head)))
  (is (= (point 1 2) ((move-direction move-direction-starting "U") :head)))
  (is (= (point 1 0) ((move-direction move-direction-starting "D") :head))))

(deftest answer-a-test
  (is (= 13 (answer-a "data/problem-09-a.txt"))))

(deftest answer-b-test
  (is (= (answer-b "Not implemented yet"))))