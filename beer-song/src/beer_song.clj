(ns beer-song
  (:require [clojure.string :as st]))

(defn get-bottles [num]
  (case num
    0 "No more bottles"
    1 "1 bottle"
    (format "%s bottles" num)))

(defn first-phrase [num]
  (str (get-bottles num) " of beer on the wall, " (st/lower-case (get-bottles num)) " of beer.\n"))

(defn second-phrase [num]
  (case num
    0 "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    (str "Take " (if (= num 1) "it" "one") " down and pass it around, " (st/lower-case (get-bottles (dec num))) " of beer on the wall.\n")))

(defn verse
  "Returns the nth verse of the song."
  [num]
  (str (first-phrase num) (second-phrase num)))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start]
   (sing start 0))
  ([start end]
   (st/join "\n" (map verse (into () (range end (inc start)))))
   ))
