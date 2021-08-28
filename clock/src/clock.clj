(ns clock)

(defn get-hours [hours minutes]
  (mod (+
   (mod hours 24)
   (quot minutes 60)
   (if (< minutes 0) -1 0)) 24))

(defn get-minutes [minutes] (mod minutes 60))

(defn clock->string [clock]
  (let [[hours minutes] clock] 
    (format "%02d:%02d" hours minutes)))

(defn clock [hours minutes]
  [(get-hours hours minutes)
   (get-minutes minutes)])

(defn add-time
  [clock time]
  (let [[hours minutes] clock]
    (clock/clock hours (+ minutes time))))
