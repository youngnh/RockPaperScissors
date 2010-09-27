(ns rps.test.core
  (:use [rps.core] :reload)
  (:use [clojure.test]
	[clojure.string :only (join)]
	[clojure.contrib.io :only (reader)])
  (:import [java.io StringReader StringWriter]))

(defmacro output-of [& body]
  `(let [writer# (StringWriter.)]
     (binding [*out* writer#]
       ~@body
       (.toString writer#))))

(deftest test-prompt-for-username
  (testing "prints prompt"
    (is (= "Player 1 Name: " (output-of (prompt-for-username "Player 1")))))

  (testing "reads response, returns player's name"
    (binding [*in* (reader (StringReader. "Ghandi\n"))
	      *out* (StringWriter.)]
      (is (= "Ghandi" (prompt-for-username "Player 1"))))))

(deftest test-prompt-for-throw
  (testing "prints prompt"
    (binding [*in* (reader (StringReader. "R\n"))]
      (is (= "[R]ock, [P]aper, or [S]cissors? " (output-of (prompt-for-throw))))))

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
    (binding [*in* (reader (StringReader. "R\nS\n"))]
      (is (starts-with? (output-of (play-round))
			(join (take 2 (repeat "[R]ock, [P]aper, or [S]cissors? ")))))))

  (testing "returns score based on who had wining throw"
    (let [rock-v-scissors (reader (StringReader. "R\nS\n"))
	  paper-v-scissors (reader (StringReader. "P\nS\n"))
	  rock-v-rock (reader (StringReader. "R\nR\n"))]
      (are [input-stream expected-score] (binding [*in* input-stream
						   *out* (StringWriter.)]
					   (= expected-score (play-round)))
	   rock-v-scissors [1 0]
	   paper-v-scissors [0 1]
	   rock-v-rock [0 0]))))

(deftest test-first-to
  (let [test-fn (first-to 3)]
    (testing "exact"
      (is (= "Ghandi" (test-fn [3 0] ["Ghandi" "Lincoln"])))
      (is (= "Lincoln" (test-fn [0 3] ["Ghandi" "Lincoln"]))))

    (testing "no one wins"
      (are [score] (nil? (test-fn score ["Ghandi" "Lincoln"]))
	   [0 0] [1 1] [1 2] [3 3]))

    (testing "both scores are above"
      (is (= "Ghandi" (test-fn [5 4] ["Ghandi" "Lincoln"])))
      (is (= "Lincoln" (test-fn [6 7] ["Ghandi" "Lincoln"])))
      (is (nil? (test-fn [8 8] ["Ghandi" "Lincoln"]))))))

(deftest test-best-of
  (let [test-fn (best-of 5)]
    (testing "exact"
      (is (= "Ghandi" (test-fn [3 0] ["Ghandi" "Lincoln"])))
      (is (= "Lincoln" (test-fn [0 3] ["Ghandi" "Lincoln"]))))

    (testing "no one wins"
      (are [score] (nil? (test-fn score ["Ghandi" "Lincoln"]))
	   [0 0] [1 1] [1 2] [2 2]))

    (testing "both scores are above"
      (is (= "Ghandi" (test-fn [5 4] ["Ghandi" "Lincoln"])))
      (is (= "Lincoln" (test-fn [6 7] ["Ghandi" "Lincoln"])))
      (is (nil? (test-fn [8 8] ["Ghandi" "Lincoln"]))))))

(deftest test-win-by
  (let [test-fn (win-by 2 :to 10)]
    (testing "exact"
      (is (= "Ghandi" (test-fn [10 8] ["Ghandi" "Lincoln"])))
      (is (= "Lincoln" (test-fn [8 10] ["Ghandi" "Lincoln"]))))

    (testing "no one wins"
      (are [score] (nil? (test-fn score ["Ghandi" "Lincoln"]))
	   [0 0] [1 1] [3 5] [9 9] [10 10]))

    (testing "must have more than your opponent"
      (is (= "Ghandi" (test-fn [15 13] ["Ghandi" "Lincoln"])))
      (is (= "Ghandi" (test-fn [11 9] ["Ghandi" "Lincoln"])))
      (is (= "Lincoln" (test-fn [15 19] ["Ghandi" "Lincoln"])))
      (is (= "Lincoln" (test-fn [17 19] ["Ghandi" "Lincoln"])))
      (is (nil? (test-fn [18 19] ["Ghandi" "Lincoln"])))
      (is (nil? (test-fn [19 19] ["Ghandi" "Lincoln"]))))))

(deftest test-scripted-games
  (testing "one throw games"
   (are [filename]
	(let [data-folder "data/RockPaperScissorsTest/"
	      expected-output-file (str data-folder filename ".expected")
	      input-file (str data-folder filename ".input")
	      expected (.. (slurp expected-output-file)
			   (replace "\n" ""))]
	  (binding [*in* (reader input-file)]
	    (= expected (.. (output-of (rps)) (replace "\n" "")))))
	"nate_wins"
	"noargs_game"))  

  (testing "win logic"
   (are [filename winlogic]
	(let [data-folder "data/RockPaperScissorsTest/"
	      expected-output-file (str data-folder filename ".expected")
	      input-file (str data-folder filename ".input")
	      expected (.. (slurp expected-output-file)
			   (replace "\n" ""))]
	  (binding [*in* (reader input-file)]
	    (= expected (.. (output-of (rps winlogic)) (replace "\n" "")))))
	"bestof_game" (best-of 5)
	"firstto_game" (first-to 3)
	"winby_game" (win-by 2 :to 3))))