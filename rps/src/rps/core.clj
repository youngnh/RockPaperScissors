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
  (= (get {:rock :scissors
	   :scissors :paper
	   :paper :rock} throw)
     other))

(defn score-round [throw other]
  (cond (beats throw other) [1 0]
	(beats other throw) [0 1]
	:otherwise [0 0]))

(defn add-score [score1 score2]
  (map + score1 score2))

(defn win-by [by _ n]
  (let [won? (fn [score1 score2]
		  (and (>= score1 n)
		       (>= (- score1 score2) by)))]
   (fn [[p1score p2score] [player1 player2]]
     (cond (won? p1score p2score) player1
	   (won? p2score p1score) player2))))

(defn first-to [n] (win-by 1 :to n))

(defn best-of [n] (first-to (inc (quot n 2))))

(defn game [winlogic [player1 p1throws] [player2 p2throws]]
  (some #(winlogic % [player1 player2]) (reductions add-score [0 0] (map score-round p1throws p2throws))))

(defn rps
  ([] (rps (first-to 1)))
  ([winlogic]
     (let [player1 [(prompt-for-username "Player 1") (repeatedly prompt-for-throw)]
	   player2 [(prompt-for-username "Player 2") (repeatedly prompt-for-throw)]
	   winner (game winlogic player1 player2)]
       (println winner "Wins!"))))