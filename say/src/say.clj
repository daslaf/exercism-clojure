(ns say
  (:require [clojure.string :as st]))

(declare
 scale
 special-numbers
 tens
 units)

(defn char-to-int [c] (. Character getNumericValue c))

(defn with-scale [nums]
  (map
   vector
   nums
   (subvec scale (- (count scale) (count nums)))))

(defn to-thousand-chunks [num]
  (->> num
       str
       (into ())
       (partition-all 3)
       (into ())
       (map #(. Integer parseInt (apply str (into () %))))))

;; -----------

(defn handle-special-numbers [num]
  (or
   (special-numbers num)
   (units num)
   (tens num)))

(defn under1000 [num]
  (or
   (handle-special-numbers num)
   (let [[cent ten unit] (take-last 3 (str "00" num))]
     (st/join
      " "
      (filter identity
              [(when (not= cent \0) (format "%s hundred" (units (char-to-int cent))))
               (when (not= [\0 \0] [ten unit])
                 (str (tens (* 10 (char-to-int ten))) "-" (units (char-to-int unit))))])))))


(defn number
  "Says a number"
  [num]
  (when (or (< num 0) (>= num 1000000000000)) (throw (IllegalArgumentException.)))
  (or
   (when (zero? num) "zero")
   (->> num
        to-thousand-chunks
        with-scale
        (reduce
         (fn [acc [amount unit]]
           (cond
             (zero? amount) acc
             (= unit "unit") (conj acc (under1000 amount))
             :else (conj acc (str (under1000 amount) " " unit))))
         [])
        (st/join " "))))

;; ------------------------------------
;; Lookup maps 
(def scale ["billion" "million" "thousand" "unit"])

(def special-numbers
  {11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"})

(def units
  {0 "zero"
   1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"})

(def tens
  {10 "ten"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})


