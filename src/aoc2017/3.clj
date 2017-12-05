(ns aoc2017.3
  (:require [clojure.string :as strings]
            [clojure.math.combinatorics :as combo]))

;;--- Day 3: Spiral Memory ---

;; You come across an experimental new kind of memory stored on an infinite two-dimensional grid.

;; Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up while spiraling outward. For example, the first few squares are allocated like this:

;; 17  16  15  14  13
;; 18   5   4   3  12
;; 19   6   1   2  11
;; 20   7   8   9  10
;; 21  22  23---> ...
;; While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 (the location of the only access port for this memory system) by programs that can only move up, down, left, or right. They always take the shortest path: the Manhattan Distance between the location of the data and square 1.

;; For example:

;; Data from square 1 is carried 0 steps, since it's at the access port.
;; Data from square 12 is carried 3 steps, such as: down, left, left.
;; Data from square 23 is carried only 2 steps: up twice.
;; Data from square 1024 must be carried 31 steps.
;; How many steps are required to carry the data from the square identified in your puzzle input all the way to the access port?

;; Your puzzle input is 289326.


(defn largest-full-shell
  "Returns the side of the largest full spiral shell"
  [n]
  (let [max-size (inc (int (Math/ceil (Math/sqrt n))))]
    (->> (range 1 (inc max-size) 2)
         reverse
         (map (fn [x] { :side x :count (* x x) }))
         (filter (fn [x] (<= (:count x) n)))
         first
         ))
)

(defn taxi-distance
  "Takes input {:x x :y x} and returns the Manhattan distance from origin"
  [coord]
  (+ 
    (Math/abs (:x coord))
    (Math/abs (:y coord))))

(defn startcoord
  "Returns the coord {:x :y} of the next layer of the shell"
  [shell] 
  {
   :x (+ 1 (/ (- (:side shell) 1) 2))
   :y (dec (/ (inc ( :side shell)) 2))
   }
  )

(defn turn-left
  [direction]
  (case direction
    { :x 0 :y 1 } { :x -1 :y 0 }
    { :x -1 :y 0 } { :x 0 :y -1 }
    { :x 0 :y -1 } { :x 1 :y 0 }
    { :x 1 :y 0 } { :x 0 :y 1 }))

(defn walk-shell
  ([shell startcoord direction distance]
   (let [side-len (inc (:side shell))
         dist-to-walk (min distance (dec side-len))
         new-direction (turn-left direction)
         new-distance (- distance dist-to-walk)
         new-coord {
                    :x (+ (* (:x direction) dist-to-walk) (:x startcoord))
                    :y (+ (* (:y direction) dist-to-walk) (:y startcoord)) }
         ]
     #ds/pp startcoord
     #ds/pp side-len
     #ds/pp dist-to-walk
     #ds/pp new-direction
     #ds/pp new-distance
     #ds/pp new-coord
     (if (> distance side-len) (walk-shell shell new-coord new-direction new-distance) new-coord)))
  ([number]
    (let [shell (largest-full-shell number)
          startcoord (startcoord shell)
          direction { :x 0 :y 1 }
          distance (- number (:count shell))]
      #ds/pp shell
      #ds/pp distance
      (if (< 0 distance)
        (walk-shell shell startcoord direction distance) { :x (dec (:x startcoord)) :y (:y startcoord) } ))))

