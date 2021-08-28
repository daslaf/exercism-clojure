(ns run-length-encoding)

(defn- update-last [coll el]
  (conj (pop coll) el))

;; -----------

(defn- serialize-string [plain-text]
  (reduce
   (fn
     [acc letter]
     (let [last-item (last acc)]
       (cond
         (nil? last-item) [{:char letter :times 1}]
         (= (:char last-item) letter) (update-last acc {:char letter :times (inc (:times last-item))})
         :else (conj acc {:char letter :times 1}))))
   []
   plain-text))

(defn- build-cipher-text [xs]
  (reduce
   (fn
     [acc {c :char t :times}]
     (if (= 1 t)
       (str acc c)
       (str acc t c)))
   ""
   xs))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (build-cipher-text (serialize-string plain-text)))

;; -------------------

(defn- is-digit? [letter] (re-find #"\d" letter))
(defn- is-closed? [item] (nil? (:char item)))

(defn- create-map [letter]
  (if (is-digit? letter)
    {:char nil :times letter}
    {:char letter :times 1}))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (->> cipher-text
       (map str)
       (reduce
        (fn [acc letter]
          (let [last-item (last acc)]
            (cond
              ;; Only for the first run
              (nil? last-item)
              (conj acc (create-map letter))

              ;; If letter is digit 
              (is-digit? letter)
              (if (is-closed? last-item)
                  ;; but last-item is not closed
                (update-last acc {:char nil :times (str (:times last-item) letter)})
                  ;; and item is closed
                (conj acc (create-map letter)))

              ;; If letter is not digit and last-item is not closed
              (is-closed? last-item)
              (update-last acc {:char letter :times (Integer/parseInt (:times last-item))})

              ;; If letter is not digit and last-item is closed
              (not= letter (:char last-item))
              (conj acc (create-map letter))
              :else acc))) [])
       (reduce (fn [acc item] (str acc (apply str (repeat (:times item) (:char item))))) "")))





