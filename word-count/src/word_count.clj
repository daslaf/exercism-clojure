(ns word-count
  (:require [clojure.string :as st]))

(defn word-count [s] ;; <- arglist goes here
  ;; your code goes here
  (reduce
   (fn [acc word]
     (assoc acc word (if (nil? (acc word)) 1 (inc (acc word))))) {} (re-seq #"\w+" (st/lower-case s))))
