(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [aoc2017.core :as system]
            [aoc2017.3]))

(def system nil)

(defn init
  []
  (alter-var-root #'system (constantly (system/system))))

(defn start
  []
  (alter-var-root #'system system/start))

(defn stop
  []
  (alter-var-root #'system (fn [s] (when s (system/stop s))))
  )

(defn go
  []
  (init)
  (start))

(defn reset
  []
  (stop)
  (refresh :after 'user/go))
