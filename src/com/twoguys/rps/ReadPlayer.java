package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class ReadPlayer implements Read<Player> {

    public List<Pair<Player, String>> read(String str) {
	Player player = new Player(str);
	Pair<Player, String> parsed = new Pair<Player, String>(player, "");
	return Arrays.asList(parsed);
    }
}