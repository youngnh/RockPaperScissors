-- file: RockPaperScissors.hs
-- Play a game of Rock, Paper, Scissors with a friend

import Control.Monad
import System
import System.IO
import System.IO.Unsafe

data Player = Player String

data Throw = Rock
           | Paper
	   | Scissors
	     deriving (Show)

beats :: Throw -> Throw -> Bool
Rock     `beats` Scissors = True
Paper    `beats` Rock     = True
Scissors `beats` Paper    = True
_        `beats` _        = False

instance Read Throw where
    readsPrec _ value = tryParse [("R", Rock), ("P", Paper), ("S", Scissors)]
    	      	    where tryParse [] = []
		    	  tryParse ((attempt, result):xs) = if (take (length attempt) value) == attempt
			  	   	      		    then [(result, drop (length attempt) value)]
							    else tryParse xs

instance Show Player where
    show (Player name) = name

instance Read Player where
    readsPrec _ value = [((Player value), "")]

rpsRound :: (Player, Throw) -> (Player, Throw) -> (Int, Int)
rpsRound (p1, t1) (p2, t2)
		 | t1 `beats` t2 = (1, 0)
		 | t2 `beats` t1 = (0, 1)
		 | otherwise     = (0, 0)

firstToWinBy :: Int -> Int -> (Player, Int) -> (Player, Int) -> Maybe Player
firstToWinBy to by (p1, p1Score) (p2, p2Score)
					 | (p1Score >= to) && ((p1Score - p2Score) >= by) = Just p1
					 | (p2Score >= to) && ((p2Score - p1Score) >= by) = Just p2
					 | otherwise   	      		  	          = Nothing

firstTo :: Int -> (Player, Int) -> (Player, Int) -> Maybe Player
firstTo x = firstToWinBy x 0

bestOf :: Int -> (Player, Int) -> (Player, Int) -> Maybe Player
bestOf x a b = firstTo x' a b
       	   where x' = (x `div` 2) + 1

type WinLogic = (Player, Int) -> (Player, Int) -> Maybe Player

game :: WinLogic -> (Player, Int) -> (Player, Int) -> [Throw] -> [Throw] -> Player
game getWinner (p1, p1Score) (p2, p2Score) p1Throws p2Throws = 
        case (getWinner (p1, p1Score) (p2, p2Score)) of
     	    Nothing -> game getWinner (p1, p1Score') (p2, p2Score') t1s t2s
            Just winner -> winner
    where (t1:t1s)                     = p1Throws
    	  (t2:t2s)		       = p2Throws
    	  (p1RoundScore, p2RoundScore) = rpsRound (p1, t1) (p2, t2)
	  (p1Score', p2Score')         = (p1Score + p1RoundScore, p2Score + p2RoundScore)

getPlayer :: Int -> IO Player
getPlayer i = do putStr prompt
	      	 hFlush stdout
	         read `liftM` getLine
	  where prompt = "Player " ++ (show i) ++ " Name: "

getThrow :: IO Throw
getThrow = do putStr prompt
	      hFlush stdout
	      read `liftM` getLine
       where prompt = "[R]ock, [P]aper, or [S]cissors? "

getAllThrows :: IO [Throw]
getAllThrows = unsafeInterleaveIO $ do throw <- getThrow
	       	                       rest <- getAllThrows
				       return (throw:rest)

getGameType :: [String] -> Either String WinLogic
getGameType ["-to", to, "-by", by] = Right (firstToWinBy (read to) (read by))
getGameType ["-bestOf", x]         = Right (bestOf (read x))
getGameType ["-to", x]             = Right (firstTo (read x))
getGameType []                     = Right (firstTo 1)
getGameType _                      = Left ("usage:\tRockPaperScissors -to to\n" ++
	    			     	   "      \tRockPaperScissors -bestof x\n" ++
					   "      \tRockPaperScissors -to to -by by")

stdIOGame :: WinLogic -> IO Player
stdIOGame logic = do p1 <- getPlayer 1
                     p2 <- getPlayer 2
	             p1Throws <- getAllThrows
	             p2Throws <- getAllThrows
	             return $ game logic (p1, 0) (p2, 0) p1Throws p2Throws

main = do args <- getArgs
       	  case getGameType args of
	      Left usage -> putStrLn usage
	      Right logic -> do winner <- stdIOGame logic
	                        putStrLn $ (show winner) ++ " Wins!"