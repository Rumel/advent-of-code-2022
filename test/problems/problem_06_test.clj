(ns problems.problem-06-test
  (:require [clojure.test :as test :refer [deftest is]]
            [problems.problem-06 :refer
             [answer-a
              answer-b
              get-arr
              packet-index]]))

(deftest get-arr-test
  (is (= [1 2 3 4] (get-arr [1 2 3] 4)))
  (is (= [2 3 4 5] (get-arr [1 2 3 4] 5))))

(deftest packet-index-test
  (is (= 7 (packet-index "mjqjpqmgbljsphdztnvjfqwrcgsmlb")))
  (is (= 5 (packet-index "bvwbjplbgvbhsrlpgdmjqwftvncz")))
  (is (= 6 (packet-index "nppdvjthqldpwncqszvftbrmjlhg")))
  (is (= 10 (packet-index "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")))
  (is (= 11 (packet-index "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))))

(deftest answer-a-test
  (is (=  7 (answer-a "data/problem-06-a.txt"))))

(deftest answer-b-test
  (is (= "Not implemented yet" (answer-a "data/problem-06-a.txt"))))