(ns rps.core)

(defn rps []
  (let [player1 (prompt-for-username "Player 1")
	player2 (prompt-for-username "Player 2")
	score (play-round)]
    (condp = score
	[1 0] (println player1 "Wins!")
	[0 1] (println player2 "Wins!"))))

(defn prompt-for-username [prompt]
  (print prompt "Name: ")
  (read-line))

(defn prompt-for-throw []
  (print "[R]ock, [P]aper, or [S]cissors? ")
  (let [response (read-line)]
    (condp = response
       "R" :rock
       "P" :paper
       "S" :scissors)))

(defn beats [throw other]
  (= other (get {:rock :scissors
		 :scissors :paper
		 :paper :rock} throw)))

(defn play-round []
  (let [throw (prompt-for-throw)
	other (prompt-for-throw)]
    (cond (beats throw other) [1 0]
	  (beats other throw) [0 1]
	  :otherwise [0 0])))