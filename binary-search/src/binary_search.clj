(ns binary-search)

(declare middle)

(defn search-for [item xs]
  (loop [from 0
         to   (count xs)]
    (let [xxs   (take to (drop from xs))
          index (middle xxs)]
      (cond
        (empty? xxs)   (throw (Error. "not found"))
        (= item (nth xxs index)) (+ from index)
        (< item (nth xxs index)) (recur from index)
        (> item (nth xxs index)) (recur (+ from (inc index)) to)))))

(defn middle [xs] (quot (count xs) 2))
