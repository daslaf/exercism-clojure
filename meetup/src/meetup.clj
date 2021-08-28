(ns meetup
  (:require [clojure.set :as set]))

;; from leap.clj

(defn leap-year? [year]
  (letfn [(divisible-by [n] (zero? (rem year n)))]
    (and
     (divisible-by 4)
     (or
      (not (divisible-by 100))
      (divisible-by 400)))))

;; ------------------

(defn num-days-in-month [year month]
  ({1  31
    2  (if (leap-year? year) 29 28)
    3  31
    4  30
    5  31
    6  30
    7  31
    8  31
    9  30
    10 31
    11 30
    12 31} month))

(def days {1 :monday
           2 :tuesday
           3 :wednesday
           4 :thursday
           5 :friday
           6 :saturday
           7 :sunday})

(defn month-starts-at [year month]
  (let [date (java.time.LocalDate/of year month 1)]
    (-> date
     (.getDayOfWeek)
     (.getValue))))

(defn month-ends-at [year month]
  (let [[y m] (if (= month 12) [(inc year) 1] [year (inc month)])
        first-day-of-next-month (month-starts-at y m)]
    (if (= first-day-of-next-month 1) 7 (dec first-day-of-next-month))))

(defn get-number-of-first-type-of-day [year month name-of-day]
  (let [day ((set/map-invert days) name-of-day)
        first-day-of-month (month-starts-at year month)]
    (cond
      (= day first-day-of-month) 1
      (> day first-day-of-month) (inc (- day first-day-of-month))
      (< day first-day-of-month) (inc (- (+ 7 day) first-day-of-month)))))

(defn get-number-of-last-type-of-day [year month name-of-day]
  (let [day ((set/map-invert days) name-of-day)
        last-day-of-month (month-ends-at year month)
        days-in-month (num-days-in-month year month)]
    (cond
      (= day last-day-of-month) days-in-month
      (> day last-day-of-month) (- days-in-month (+ 7 (- last-day-of-month day)))
      (< day last-day-of-month) (- days-in-month (- last-day-of-month day)))))

(defn get-number-of-teenth-type-of-day [year month name-of-day]
  (loop [day (get-number-of-first-type-of-day year month name-of-day)]
    (if (and (>= day 13) (<= day 19))
      day
      (recur (+ 7 day)))))


;; Firma
;; (meetup/meetup 3 2013 :sunday :first)
(defn meetup [month year name-of-day position]
  (let [day (case position
              :last (get-number-of-last-type-of-day year month name-of-day)
              :teenth (get-number-of-teenth-type-of-day year month name-of-day)
              (+ (get-number-of-first-type-of-day year month name-of-day)
                 (* 7 (.indexOf [:first :second :third :fourth] position))))]
    [year month day]))

