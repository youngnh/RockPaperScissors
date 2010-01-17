package com.twoguys.rps;

import com.twoguys.util.*;

public class FirstTo implements WinLogic {

    public FirstTo(int to) {
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	return new Nothing();
    }

}