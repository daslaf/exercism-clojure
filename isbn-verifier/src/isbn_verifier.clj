(ns isbn-verifier
  (:require [clojure.string :as st]))

;; Verification digit logic
(defn valid-verifier? [isbn]
  (let [digits (map #(if (= % \X) 10 (Integer/parseInt (str %))) isbn)]
    (->>   (map vector digits (into () (range 1 11)))
         (map #(apply * %))
         (reduce +)
         (#(= 0 (mod % 11))))))
         

;; Has expected number of chars
(defn- right-length? [isbn] (= 10 (count isbn)))

;; Is well formed
(defn- well-formed? [isbn] (re-find #"\d{9}[\dX]" isbn))

(defn isbn? [isbn]
    (every? 
     #(% (st/replace isbn #"-" "")) 
     [right-length?
      well-formed?
      valid-verifier?]))
