package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class ReadThrow implements Read<Throw> {

    public List<Pair<Throw, String>> read(String str) {
	Pair<Throw, String> parsed = new Pair<Throw, String>(new Rock(), "");
	return Arrays.asList(parsed);
    }

}