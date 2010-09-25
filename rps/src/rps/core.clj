(ns rps.core)

(defn rps []
  (prompt-for-username "Player 1")
  (prompt-for-username "Player 2"))

(defn prompt-for-username [prompt]
  (print prompt "Name: "))

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