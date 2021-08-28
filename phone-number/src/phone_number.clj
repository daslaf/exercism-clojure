(ns phone-number)

(defn clean-chars [num-string] 
  (apply str (take-last 11 (conj (re-seq #"\d" num-string) "1"))))

(defn split-in-codes [sanitized-num-string]
  (next (re-matches #"^1([2-9]{1}\d{2})([2-9]{1}\d{2})(\d{4})" sanitized-num-string)))

(defn sanitize-num-string [num-string]
  (split-in-codes (clean-chars num-string)))

(defn number [num-string]
  (let [grouped-in-codes (sanitize-num-string num-string)]
    (if (nil? grouped-in-codes)
      "0000000000"
      (apply str grouped-in-codes))))

(defn area-code [num-string]
  (first (sanitize-num-string num-string)))

(defn pretty-print [num-string]
  (let [[area-code exchange-code subscriber-number] (sanitize-num-string num-string)]
    (str "(" area-code  ") " exchange-code "-" subscriber-number))
)
