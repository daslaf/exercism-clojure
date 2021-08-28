(ns nucleotide-count)

(declare nucleotide-counts)
(def nucleotides #{\A \G \C \T})

(defn count-of-nucleotide-in-strand [nucleotide strand]
  (when (nil? (nucleotides nucleotide)) (throw (Error. "This is not a nucleotide")))
  ((nucleotide-counts strand) nucleotide))

(defn nucleotide-counts [strand]
  (reduce #(update %1 %2 inc) {\A 0, \G 0, \C 0, \T 0} strand))
