(ns etl
  (:require [clojure.string :as str]))

(defn transform [source]
  (->> (seq source)
       (mapcat (fn
                 [[index letters]]
                 (map #(vector (str/lower-case %) index) letters)))
       (into {})))
