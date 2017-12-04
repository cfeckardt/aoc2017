(ns aoc2017.3-test
  (:require [clojure.test :refer :all]
            [aoc2017.3 :refer :all]))

(deftest largest-full-shell-test
  (testing "the largest full shell"
    (are [input output] (= (largest-full-shell input) output)
      1 1
      2 1
      9 3
      22 3
      25 4
    )))
