(ns rps.core)

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

(defn play-round-and-score [scores]
  (map + scores (play-round)))

(defn win-by [by _ n]
  (fn [[p1score p2score] [player1 player2]]
    (cond (and (>= p1score n)
	       (>= (- p1score p2score) by)) player1
	  (and (>= p2score n)
	       (>= (- p2score p1score) by)) player2)))

(defn first-to [n] (win-by 1 :to n))

(defn best-of [n] (first-to (inc (quot n 2))))

(defn rps
  ([] (rps (first-to 1)))
  ([winlogic]
     (let [player1 (prompt-for-username "Player 1")
	   player2 (prompt-for-username "Player 2")
	   winner (some #(winlogic % [player1 player2])
			(iterate play-round-and-score [0 0]))]
       (println winner "Wins!"))))