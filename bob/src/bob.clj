(ns bob
  (:require [clojure.string :as st]))

(defn upper? [s]
  (let [sanitized (apply str (re-seq #"[a-zA-Z]+" s))]
    (and (not= sanitized "") (= sanitized (st/upper-case sanitized)))))

(defn response-for [s]
  (let [trimmed (st/trim s)
        is-question (st/ends-with? trimmed "?")
        is-yelling  (upper? trimmed)]
    (cond
      (= "" trimmed) "Fine. Be that way!"
      (and is-yelling is-question) "Calm down, I know what I'm doing!"
      is-yelling "Whoa, chill out!"
      is-question "Sure."
      :else "Whatever.")))
