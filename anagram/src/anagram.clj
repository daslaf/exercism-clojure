(ns anagram
  (:require [clojure.string :as st]))

(defn anagrams-for [word prospect-list]
  (let [lword (st/lower-case word)]
    (filter
     (fn [w]
       (let [lw (st/lower-case w)]
         (and
          (not= lword lw)
          (= (sort lword) (sort lw))))) 
     prospect-list)))
 