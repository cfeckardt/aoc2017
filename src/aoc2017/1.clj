(ns aoc2017.1)

;; Solutions to problems from day 1.

(defn- char->num
  [x]
  (- (int x) 48))

(defn repeat-chars
  ([string offset]
  (let [sequ (seq string)
        csequ (cycle (seq string))]
    (->>
      (map-indexed
        (fn [idx itm]
          (if (= itm (nth csequ (+ idx offset)))
            itm
            nil)) sequ)
      (remove nil?)
      (apply str))))
  ([string]
   (repeat-chars string 1))
  )

(defn special-sum
  [numbers]
  (->>
    (seq (repeat-chars numbers))
    (map char->num)
    (apply +)
    )
  )

(defn special-sum2
  [numbers]
  (->>
    (seq (repeat-chars numbers (/ (count numbers) 2)))
    (map char->num)
    (apply +)
    )
  )
