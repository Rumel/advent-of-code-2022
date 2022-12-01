(ns problems.problem-01-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-01 :refer [answer-a answer-b get-calorie-totals max-calories convert-to-vec]]))

(deftest get-calorie-totals
  (is (= [6000 4000 11000 24000 10000]
         (get-calorie-totals [[1000 2000 3000] [4000] [5000 6000] [7000 8000 9000] [10000]]))))

(deftest max-calories
  (is (= 24000 (max-calories [6000 4000 11000 24000 10000]))))

(deftest convert-to-vec
  (is (= [[1 2 3] [4 5]] (convert-to-vec "1\n2\n3\n\n4\n5\n"))))

(deftest answer-a-test
  (is (= (answer-a "data/problem-01-a.txt") 24000)))

(deftest answer-b-test
  (is (= (answer-b "data/problem-01-a.txt") "Not implemented yet")))