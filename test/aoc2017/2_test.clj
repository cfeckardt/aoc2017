(ns aoc2017.2-test
  (:require [clojure.test :refer :all]
            [aoc2017.2 :refer :all]))

(def inputstr
  (slurp "test/aoc2017/2_input.txt"))

(deftest checksum-test
  (testing "Checksum of a string"
    (are [input output] (= ((partial checksum row-diff) input) output)
      "145" 4
      "222" 0
      "332 333\n193 199" 7
      inputstr 5))

  (testing "Divisibles"
    (are [input output] (= ((partial checksum even-divider-sum) input) output)
      "28 2" 14
      "36 3" 12)))
