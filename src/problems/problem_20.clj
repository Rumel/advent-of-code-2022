(ns problems.problem-20
  (:require [common.helpers :refer [debug input]]))

(def decryption-key 811589153)

(defn hash-values
  [values use-decryption-key?]
  (map-indexed
   (fn [index item]
     (let [item (if use-decryption-key?
                  (* item decryption-key)
                  item)]
       [(hash (str index "-" item)) item]))
   values))

(defn move-value
  [v value]
  (let [c (count v)
        v (vec v)
        index (.indexOf v value)
        new-index (mod (+ index (second (nth v index))) (dec c))
        temp-list (into [] (concat (subvec v 0 index)
                                   (subvec v (inc index))))
        return-list (into [] (concat (subvec temp-list 0 new-index)
                                     [value]
                                     (subvec temp-list new-index)))]
    return-list))


(let [v (vec (concat (subvec [0 1 2 3 4] 0 0)
                     (subvec [0 1 2 3 4] 1)))
      a (concat (subvec v 0 1)
                [0]
                (subvec v 1))]
  a)

(defn mix-values
  [hashed-values times]
  (let [result
        (reduce
         (fn [values _]
           (loop [hashed-values hashed-values
                  values values]
             (if (nil? (first hashed-values))
               values
               (let [value (first hashed-values)]
                 (recur (rest hashed-values) (move-value values value))))))
         hashed-values
         (range 0 times))]
    (map second result)))

(defn compute-answer
  [values]
  (let [index (.indexOf values 0)
        c (count values)
        x (mod (+ index 1000) c)
        y (mod (+ index 2000) c)
        z (mod (+ index 3000) c)]
    (+ (nth values x)
       (nth values y)
       (nth values z))))

(defn parsed-input
  [file]
  (->> file
       input
       (map read-string)))

(defn answer-a
  [file]
  (-> file
      parsed-input
      (hash-values false)
      (mix-values 1)
      compute-answer))

(defn answer-b
  [file]
  (-> file
      parsed-input
      (hash-values true)
      (mix-values 10)
      compute-answer))


(defn answer []
  (time
   (println "20: A:" (answer-a "data/problem-20-input.txt")))
  (time
   (println "20: B:" (answer-b "data/problem-20-input.txt"))))