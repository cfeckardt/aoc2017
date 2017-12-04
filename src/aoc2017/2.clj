(ns aoc2017.2
  (:require [clojure.string :as strings]
            [clojure.math.combinatorics :as combo]))

(defn- row->numbers
  [row]
  (Integer/parseInt row))

(defn- str->row
  [string]
  (strings/split string #"\s+"))

(defn- str->rows
  [string]
  (->>
  (strings/split-lines string)
  (map (fn [row]
         (map row->numbers (str->row row))))))

(defn row-diff
  [row]
  (let [
        maxi (apply max row)
        mini (apply min row)]
    (- maxi mini)))


(defn tuples
  [x coll]
  (map (fn [y] [x y]) coll))

(defn all-pairs
  [coll]
  (let [n (count coll)]
    (mapcat identity (map-indexed
      (fn [idx itm]
        (let
          [remains (take (- n (inc idx)) (nthrest (cycle coll) (inc idx)))]
          (tuples itm remains))) coll))))

(defn even-divider-sum
  [row]
  (let [divvy (all-pairs row)]
    (->> divvy
      (map sort)
      (filter (
        fn [x]
          (let [lhs (first x)
                rhs (last x)]
            (= 0 (mod rhs lhs)))))
      (map (
        fn [x]
          (let [lhs (first x)
                rhs (last x)]
          (/ rhs lhs))
          ))
      (apply +)
      )
    )
  )

(defn checksum
  [fun strings]
  (apply + (->>
    (str->rows strings)
    (map fun))))
