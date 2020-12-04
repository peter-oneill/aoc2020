(def input *command-line-args*)

(defn x_coord_tree [slope x_coord]
  (if (= (nth slope (mod x_coord (count slope))) \#) 1 0))

(defn traverse [d r slope]
  (loop [remaining_slope (rest slope)
         x_coord r
         trees 0]
    (if (empty? remaining_slope)
        trees
      (recur (rest remaining_slope)
             (+ x_coord r)
             (+ trees (x_coord_tree (first remaining_slope) x_coord))))))

(println (traverse 1 3 input))

