(ns series)

(defn slices [string length]
  (loop [substring string acc []]
    (cond
      (= length 0) [""]
      (> length (count substring)) []
      (= length (count substring)) (conj acc substring)
      :else (recur (subs substring 1) (conj acc (subs substring 0 length))))))
  