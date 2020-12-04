(defn parse_item [[cnt letter passwd]]
  (let [[ix1 ix2] (clojure.string/split cnt #"-")]
    {:ix1 (Integer. ix1)
     :ix2 (Integer. ix2)
     :letter (str (first letter))
     :passwd passwd}))

(defn parse_list [l]
  (loop [[c l p & rest] l out []]
   (if (not c)
     out
     (recur rest (conj out (parse_item [c l p]))))))

(defn xor [a b] ((if a not identity) b))

(defn letter_match [string letter]
  #(= (nth string (dec %)) (first letter)))

(defn p2_valid [{:keys [ix1 ix2 letter passwd]}]
  (let [matcher (letter_match passwd letter)]
   (xor (matcher ix1) (matcher ix2))))

(println (count (filter p2_valid (parse_list *command-line-args*))))
