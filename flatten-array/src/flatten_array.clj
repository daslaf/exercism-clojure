(ns flatten-array)

(defn flatten [arr] ;; <- arglist goes here
  (filter identity (clojure.core/flatten arr)))
