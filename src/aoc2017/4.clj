(ns aoc2017.4
  (:require [clojure.string :as strings])
  )

(defn valid-password
  [password]
  (let [words (strings/split password #"\s+")
        num-words (count words)
        distinct-words (count (distinct words))
        ]
    (= num-words distinct-words)))

(defn- sort-word [word]
  (apply str (sort word)))

(defn valid-password2
  [password]
  (let [words (map sort-word (strings/split password #"\s+"))
        num-words (count words)
        distinct-words (count (distinct words))
        ]
    (= num-words distinct-words)))


(defn valid-count
  [passwords]
  (->> (strings/split-lines passwords)
    (filter valid-password)
    (count)))

(defn valid-count2
  [passwords]
  (->> (strings/split-lines passwords)
    (filter valid-password2)
    (count))) 
