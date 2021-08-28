(ns gigasecond)

(def gs 1000000000)

(defn to-timestamp [[year month day]]
  (-> (java.time.LocalDate/of year month day)
      (.atStartOfDay (java.time.ZoneId/systemDefault))
      (.toInstant)
      (.getEpochSecond)))

(defn to-date [seconds]
  (let [local-date (-> (java.time.Instant/ofEpochSecond seconds)
                       (.atZone (java.time.ZoneId/systemDefault))
                       (.toLocalDate))]
    [(.getYear local-date)
     (.getValue (.getMonth local-date))
     (.getDayOfMonth local-date)]))

(defn from [year month day]
    (to-date (+ gs (to-timestamp [year month day]))))
