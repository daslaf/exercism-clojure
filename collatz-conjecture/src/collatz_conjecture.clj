(ns collatz-conjecture)

(defn collatz [num]
  (when (< num 1) (throw (IllegalArgumentException. "Input should be at least 1")))
  (loop [n num c 0]
    (if 
     (= n 1) 
      c   
      (recur
       (if (even? n) (/ n 2) (inc (* n 3))) 
       (inc c)))))
