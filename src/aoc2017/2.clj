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

(defn- all-pairs
  [coll]
  (map-indexed (fn [idx itm]
                (tuples itm (nthrest (cycle coll) (inc idx)))) coll))

(defn even-divider-sum
  [row]
  (let [divvy (all-pairs row)]
    (->>
      (filter (
        fn [x]
          (let [lhs (first x)
                rhs (last x)]
            (or
              (= 0 (mod lhs rhs))
              (= 0 (mod rhs lhs)))))
        divvy)
      first
      (apply +)
      )
    )
  )

(defn checksum
  [fun strings]
  (apply + (->>
    (str->rows strings)
    (map fun))))
