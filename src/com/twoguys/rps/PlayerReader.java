package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class PlayerReader implements Read<Player> {

    public List<Pair<Player, String>> read(String s) {
	Player player = new Player(s);
	Pair<Player, String> parsed = new Pair(player, "");
	return Arrays.asList(parsed);
    }

}