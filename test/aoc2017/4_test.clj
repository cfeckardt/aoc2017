(ns aoc2017.4-test
  (:require [clojure.test :refer :all]
            [aoc2017.4 :refer :all]))

(def inputstr
  (slurp "test/aoc2017/4_input.txt"))

(deftest valid-count-test
  (testing "the largest full shell"
    (are [input output] (= (valid-count input) output)
      "aa aa" 0
      "aa bb" 1
      inputstr 386
    )))

(deftest valid-count2-test
  (testing "the largest full shell"
    (are [input output] (= (valid-count2 input) output)
      "aa aa" 0
      "aa bb" 1
      inputstr 208
    )))
