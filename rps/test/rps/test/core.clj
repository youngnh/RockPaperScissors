(ns rps.test.core
  (:use [rps.core] :reload)
  (:use [clojure.test]
	[clojure.contrib.io :only (reader)])
  (:import [java.io StringWriter]))

(deftest test-scripted-games
  (let [string-writer (StringWriter.)
	expected (slurp "data/RockPaperScissorsTest/firstto_game.expected")]
   (binding [*in* (reader "data/RockPaperScissorsTest/firstto_game.input")
	     *out* string-writer]
     (rps)
     (is (= expected (.toString string-writer))))))

(deftest test-prompt-for-username
  (let [string-writer (StringWriter.)]
    (binding [*out* string-writer]
      (prompt-for-username "Player 1")
      (is (= "Player 1 Name: " (.toString string-writer))))))