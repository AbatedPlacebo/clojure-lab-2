(ns clojure-lab-2.core)

(defn cube [x] (* x x x))

(defn sum-lazy [term a nxt h]
  (lazy-seq
   (cons (* (/ h 2) (term a)) (sum-lazy term (nxt a) nxt h))))


(defn lazy-integral [f a b n]
  (let [h (double (/ (- b a) n))
        yk (fn [k] (f (+ a (* h k))))
        trapezoid-term (fn [k] (*
                                (cond
                                  (or (= k 0) (= k n)) 1
                                  :else 2)
                                (yk k)))]
   (sum-lazy trapezoid-term 0 inc h) 
    ))

(defn integral-reductions [f a b n]
 (reductions + (lazy-integral f a b n)) 
  )

(let [ls (integral-reductions cube 0 3 100000)] 
(println (nth ls 100000))
(println (nth ls (dec 100000)))
(println (nth ls (inc 100000)))
)
