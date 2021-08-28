(ns perfect-numbers)

(defn find-multiples [num] 
  (let [candidates (range 1 (inc (quot num 2)))]
    (filter (fn [n] (zero? (rem num n))) candidates)))

(defn classify [num]
  (when (< num 0) (throw (IllegalArgumentException.)))
  (let [aliquot-sum (apply + (find-multiples num))]
    (cond
      (= aliquot-sum num) :perfect
      (> aliquot-sum num) :abundant
      (< aliquot-sum num) :deficient)))

