(ns rps.test.core
  (:use [rps.core] :reload)
  (:use [clojure.test]
	[clojure.string :only (join)]
	[clojure.contrib.io :only (reader)])
  (:import [java.io StringReader StringWriter]))

(deftest test-scripted-games
  (are [filename]
       (let [data-folder "data/RockPaperScissorsTest/"
	     expected-output-file (str data-folder filename ".expected")
	     input-file (str data-folder filename ".input")
	     string-writer (StringWriter.)
	     expected (.. (slurp expected-output-file)
			  (replace "\n" ""))]
	 (binding [*in* (reader input-file)
		   *out* string-writer]
	   (rps)
	   (= expected (.toString string-writer))))

       "bestof_game"
       "firstto_game"
       "nate_wins"
       "noargs_game"
       "winby_game"))

(deftest test-prompt-for-username
  (let [string-writer (StringWriter.)]
    (binding [*out* string-writer]
      (prompt-for-username "Player 1")
      (is (= "Player 1 Name: " (.toString string-writer))))))

(deftest test-prompt-for-throw
  (testing "prints prompt"
    (let [string-writer (StringWriter.)]
      (binding [*in* (reader (StringReader. "R\n"))
		*out* string-writer]
       (prompt-for-throw)
       (is (= "[R]ock, [P]aper, or [S]cissors? " (.toString string-writer))))))

  (testing "reads response, returns throw"
    (binding [*in* (reader (StringReader. "R\nP\nS\n"))
	      *out* (StringWriter.)]
      (is (= :rock (prompt-for-throw)))
      (is (= :paper (prompt-for-throw)))
      (is (= :scissors (prompt-for-throw))))))

(def starts-with? (memfn startsWith prefix))

(deftest test-beats
  (testing "R > S > P > R"
    (are [throw other] (beats throw other)
	 :rock :scissors
	 :scissors :paper
	 :paper :rock))

  (testing "R < P < S < R"
    (are [throw other] (not (beats throw other))
	 :rock :paper
	 :paper :scissors
	 :scissors :rock)))

(deftest test-play-round
  (testing "prompts both players for a throw"
    (let [string-writer (StringWriter.)]
      (binding [*in* (reader (StringReader. "R\nS\n"))
		*out* string-writer]
	(play-round)
	(is (starts-with? (.toString string-writer)
			  (join (take 2 (repeat "[R]ock, [P]aper, or [S]cissors? "))))))))

  (testing "returns score based on who had wining throw"
    (let [rock-v-scissors (reader (StringReader. "R\nS\n"))
	  paper-v-scissors (reader (StringReader. "P\nS\n"))
	  rock-v-rock (reader (StringReader. "R\nR\n"))]
      (testing "Rock v. Scissors: rock wins"
	(binding [*in* rock-v-scissors
		 *out* (StringWriter.)]
	 (is (= [1 0] (play-round)))))

      (testing "Paper v. Scissors: paper loses"
	(binding [*in* paper-v-scissors
		  *out* (StringWriter.)]
	  (is (= [0 1] (play-round)))))

      (testing "Rock v. Rock: tie"
	(binding [*in* rock-v-rock
		  *out* (StringWriter.)]
	  (is (= [0 0] (play-round))))))))
