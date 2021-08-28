(ns rna-transcription)

;; DNA RNA
;; G -> C
;; C -> G
;; T -> A
;; A -> U

(def dna->rna
  {\G \C
   \C \G
   \T \A
   \A \U})

(defn to-rna [dna]
  (apply
   str
   (map
    (fn
      [nucleotid]
      (let [complement (dna->rna nucleotid)]
        (if (nil? complement)
           (throw (AssertionError. "Wrong input."))
           (format "%c" complement)))
      )
    dna)))
















