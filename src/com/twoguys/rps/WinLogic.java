package com.twoguys.rps;

import com.twoguys.util.*;

public interface WinLogic {

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2);

}