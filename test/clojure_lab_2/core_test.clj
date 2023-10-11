(ns clojure-lab-2.core-test
  (:require
   [clojure.test :refer :all]
   [clojure-lab-2.core :refer :all]
   ))

(deftest integration-test
  (defn square [x] (* x x))
  (defn sqrt [x] (Math/sqrt x))
  (testing "FIXME, I fail."
    (is (= 72 (Math/round (integral square 0 6 100))))
    (is (= 9597 (Math/round (integral square 10 31 100))))
    (is (= 21 (Math/round (integral sqrt 0 10 100))))
    (is (= 7453 (Math/round (integral sqrt 0 500 200))))
    ))

(run-tests 'clojure-lab-2.core-test)
