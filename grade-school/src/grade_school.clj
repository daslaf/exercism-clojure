(ns grade-school)

(defn grade [school grade]  ;; <- arglist goes here
  (get school grade []))

(defn add [school name grade]
  (update school grade #(conj (or % []) name)))

(defn sorted [school]
  (->> school
       seq
       (mapcat (fn [[k v]] [k (sort v)]))
       (apply sorted-map)))
