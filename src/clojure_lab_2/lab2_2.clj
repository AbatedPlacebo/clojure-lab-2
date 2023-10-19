(ns clojure-lab-2.core)

(defn cube [x] (* x x x))

(defn sum-lazy [term a nxt]
  (lazy-seq
   (cons (term a) (sum-lazy term (nxt a) nxt))))

(defn sum-ready-lazy [n h term]
  (lazy-seq (cons (* ( / h 2) (reduce + (take (+ n 1) (sum-lazy term 0 inc)))) (sum-ready-lazy n h term)))
  )

(defn lazy-integral [f a b n]
  (let [h (double (/ (- b a) n))
        yk (fn [k] (f (+ a (* h k))))
        trapezoid-term (fn [k] (*
                                (cond
                                  (or (= k 0) (= k n)) 1
                                  :else 2)
                                (yk k)))]
   (take 1 (sum-ready-lazy n h trapezoid-term))
    ))

(time (lazy-integral cube 0 3 10))
(time (integral cube 0 3 100))
(time (lazy-integral #(Math/exp %) 2 6 100))
