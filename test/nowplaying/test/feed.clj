(ns nowplaying.test.feed
  (:use clojure.test nowplaying.models.feed)
  (:require [clojure.data.json :as json]
            [clojure.xml :as xml]))

(deftest test-feed
  (testing "q2 parsing"
    (let [data (translate-q2 (json/read-str (slurp "test/data/q2.json")))]
      (is (= (:title data) "Turangalila-symphonie"))
      (is (= (:composer data) "Olivier Messiaen"))))

  (testing "counterstream parsing"
  (let [data (translate-counterstream (xml/parse "test/data/counterstream.xml"))]
    (is (= (:title data) "Serenade"))
    (is (= (:composer data) "Edward T. Cone"))))

  (testing "second inversion parsing"
  (let [data (translate-second-inversion (xml/parse "test/data/second-inversion.xml"))]
    (is (= (:title data) "Violin Sonata No.1"))
    (is (= (:composer data) "Frederic Delius"))))

  (testing "yle parsing"
  (let [data (translate-yle (xml/parse "test/data/yle.xml"))]
    (is (= (:title data) "Godard: Pianokonsertto n:o 1 a-molli. (Howard Shelley ja Tasmanian SO)."))
    (is (= (:composer data) "Godard, Benjamin [1849-1895]")))))
