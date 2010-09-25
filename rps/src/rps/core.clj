(ns rps.core)

(defn rps []
  (prompt-for-username "Player 1")
  (prompt-for-username "Player 2"))

(defn prompt-for-username [prompt]
  (print prompt "Name: "))

(defn prompt-for-throw []
  (print "[R]ock, [P]aper, or [S]cissors? "))
