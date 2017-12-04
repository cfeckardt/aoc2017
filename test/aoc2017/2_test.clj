(ns aoc2017.2-test
  (:require [clojure.test :refer :all]
            [aoc2017.2 :refer :all]))

(def inputstr
  (slurp "test/aoc2017/2_input.txt"))

(deftest all-pairs-test
  (testing "foo"
    (are [input output] (= (all-pairs input) output)
      [1 2 3] '([1 2] [1 3] [2 3])
    )))


(deftest checksum-test
  (testing "Checksum of a string"
    (are [input output] (= ((partial checksum row-diff) input) output)
      "145" 0
      "222" 0
      "332 333\n193 199" 7
      inputstr 45972))

  (testing "Divisibles"
    (are [input output] (= ((partial checksum even-divider-sum) input) output)
      "28 2" 14
      "36 3" 12
      "2 3 8 6" 9
      inputstr 326)))
