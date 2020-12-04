(defn parse_item [[cnt letter passwd]]
  (let [[min_cnt max_cnt] (clojure.string/split cnt #"-")]
    {:min (Integer. min_cnt)
     :max (Integer. max_cnt)
     :letter (str (first letter))
     :passwd passwd}))

(defn parse_list [l]
  (loop [[c l p & rest] l out []]
   (if (not c)
     out
     (recur rest (conj out (parse_item [c l p]))))))

(defn p1_valid [passwd]
  (let [letter_count (count (re-seq (re-pattern (:letter passwd)) (:passwd passwd)))]
        (and (<= (:min passwd) letter_count) (>= (:max passwd) letter_count))))

(println (count (filter p1_valid (parse_list *command-line-args*))))
