(ns grains)

(defn square [n]
  (apply *' (repeat (dec n) 2)))

(defn total []
  (apply + (map square (range 1 65))))

(defn total2 []
  (loop [total-sum 0
         tile      1
         grains    1]
    (let [new-grains (if (= tile 1) 1 (*' 2 grains))]
      (if
       (= tile 65)
        total-sum
        (recur
         (+ total-sum new-grains)
         (inc tile)
         new-grains)))))

