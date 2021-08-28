(ns leap)

(defn leap-year? [year]
  (letfn [(divisible-by [n] (zero? (rem year n)))]
    (and
     (divisible-by 4)
     (or
      (not (divisible-by 100))
      (divisible-by 400)))))
