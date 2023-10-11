(ns clojure-lab-2.core)

; memoize function cube

(defn cube [x] (* x x x))

(defn sum [term a nxt b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (nxt a) nxt b))))

(defn lazy_integral [f a b n]
  (let [h (/ (- b a) n)
        yk (fn [k] (f (+ a (* h k))))
        trapezoid-term (fn [k] (*
                              (cond
                                (or (= k 0) (= k n)) 1
                                :else 2)
                              (yk k)))]
    (* (/ h 2) ((memoize sum) trapezoid-term 0 inc n))))

(time (integral cube 0 3 100))
(time (integral #(Math/exp %) 2 6 1000))
