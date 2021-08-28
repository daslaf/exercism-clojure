(ns space-age)

;; (defn to-earth-years [seconds])

(def seconds-in-earth 31557600)

(defn on-mercury [seconds]
  (/ seconds (* seconds-in-earth 0.2408467)))

(defn on-venus [seconds]
  (/ seconds (* seconds-in-earth 0.61519726)))

(defn on-earth [seconds]
  (/ seconds seconds-in-earth))

(defn on-mars [seconds]
  (/ seconds (* seconds-in-earth 1.8808158)))

(defn on-jupiter [seconds] (/ seconds (* seconds-in-earth 11.862615)))

(defn on-saturn [seconds] (/ seconds (* seconds-in-earth 29.447498)))

(defn on-neptune [seconds] (/ seconds (* seconds-in-earth 164.79132)))

(defn on-uranus [seconds] (/ seconds (* seconds-in-earth 84.016846)))
