(ns hamming)

(defn distance [strand1 strand2]
  (when (= (count strand1) (count strand2)) 
    (reduce 
     (fn [acc [char1 char2]] (if (not= char1 char2) (inc acc) acc)) 
     0 
     (map vector strand1 strand2))))
