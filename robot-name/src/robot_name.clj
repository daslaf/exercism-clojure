(ns robot-name)

(def name-cache (atom #{}))

(defn get-letter []
  (let [codepoint (rand-nth (range 65 91))]
    (java.lang.String. (java.lang.Character/toChars codepoint))))
(defn get-digit []
  (format "%03d" (rand-int 1000)))

(defn- gen [] (str (get-letter) (get-letter) (get-digit)))

(defn generate-name []
  (let [name (gen)]
    (if 
     (nil? (@name-cache name))
      (do
        (swap! name-cache (fn [old] (conj old name)))
        name)
      (recur))))

(defn robot []
  (atom (generate-name)))

(defn robot-name [robot] @robot)

(defn reset-name [robot]
  (reset! robot (generate-name)))
