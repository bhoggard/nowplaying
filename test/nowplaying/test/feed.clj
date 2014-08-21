(ns nowplaying.test.feed
  (:use clojure.test nowplaying.models.feed)
  (:require [clojure.data.json :as json]))

(deftest test-feed
  (testing "q2 parsing"
    (let [data (translate-q2 (json/read-str (slurp "test/data/q2.json")))]
      (is (= (:title data) "Turangalila-symphonie"))
      (is (= (:composer data) "Olivier Messiaen")))))

