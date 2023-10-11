(ns clojure-lab-2.core)

(defn cube [x] (* x x x))

(def memoize-cube (memoize cube))

(defn sum [term a nxt b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (nxt a) nxt b))))

(defn sum-iterate [term a nxt b]
  (loop [acc 0
         a a]
    (if (> a b)
      acc
      (recur (+ acc (term a) ) (nxt a)))))

(defn integral [f a b n]
  (let [h (double (/ (- b a) n))
        yk (fn [k] (f (+ a (* h k))))
        trapezoid-term (fn [k] (*
                                (cond
                                  (or (= k 0) (= k n)) 1
                                  :else 2)
                                (yk k)))]
    (* (/ h 2) ((memoize sum-iterate) trapezoid-term 0 inc n))))

(time (integral memoize-cube 2 10 10000))
(time (integral #(Math/exp %) 1 7 100))
