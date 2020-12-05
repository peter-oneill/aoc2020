(def input *command-line-args*)

(defn x_coord_tree [slope x_coord]
  (if (= (nth slope (mod x_coord (count slope))) \#) 1 0))

(defn traverse [r d slope]
  (loop [remaining_slope (subvec (vec slope) d)
         x_coord r
         trees 0]
    (if (< (count remaining_slope) d)
      trees
      (recur (subvec remaining_slope d)
             (+ x_coord r)
             (+ trees (x_coord_tree (first remaining_slope) x_coord))))))

(def slopes '([1 1]
              [3 1]
              [5 1]
              [7 1]
              [1 2]))

(defn single_slope [[r d]] (traverse r d input))

(println (reduce * (map single_slope slopes)))

