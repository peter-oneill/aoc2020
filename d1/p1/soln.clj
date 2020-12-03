; Turn input args from strings into single integer lists
(defn strings_to_wrapped_ints [input_strings] 
  (map #(list (Integer. %1)) input_strings))
(assert (= 
  (strings_to_wrapped_ints '("1" "2" "3"))
  '((1) (2) (3))))

; Turn a single value into a list of n duplicates e.g
(defn copy_val [value times]
  (loop [ii times
         out '()]
    (if (= ii 0)
      out
      (recur (- ii 1) (conj out value)))))
(assert (= 
  (copy_val :val 3)
  '(:val :val :val)))

; Combines collections to give all combinations of items contained.  See tests
; for examples.
(defn list_combinations
  [col1 col2] (mapcat (fn [x] (map (fn [y] (apply conj x y)) col2)) col1)
)
(assert (=
  (list_combinations [[1] [2]] [[:a] [:b]])
  [[1 :a] [1 :b] [2 :a] [2 :b]]))
(assert (=
  (list_combinations [[1 :a] [2 :a] [1 :b] [2 :b]] [["c"] ["d"]]))
  [[1 :a "c"] [1 :a "d"] [2 :a "c"] [2 :a "d"] [1 :b "c"] [1 :b "d"] [2 :b "c"] [2 :b "d"]])

(def nums (strings_to_wrapped_ints (rest *command-line-args*)))

(def combos (reduce list_combinations (copy_val nums (Integer. (first *command-line-args*)))))

(println (first (filter #(not= () %1) (map #(reduce * %1) (filter #(= 2020 (reduce + %1)) combos)))))
