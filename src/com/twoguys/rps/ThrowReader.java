package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class ThrowReader implements Read<Throw> {

    private String value;

    public List<Pair<Throw, String>> read(String s) {
	this.value = s;
	return tryParse(Arrays.asList(new Pair<String, Throw>("R", new Rock()),
				      new Pair<String, Throw>("P", new Paper()),
				      new Pair<String, Throw>("S", new Scissors())));
    }    

    public List<Pair<Throw, String>> tryParse(List<Pair<String, Throw>> attempts) {
	if(attempts.isEmpty()) {
	    return tryParse();
	} else {
	    Pair<String, Throw> attempt = attempts.remove(0);
	    return tryParse(attempt.a, attempt.b, attempts);
	}
    }

    public List<Pair<Throw, String>> tryParse() {
	return Arrays.asList();
    }

    public List<Pair<Throw, String>> tryParse(String attempt, Throw result, List<Pair<String, Throw>> attempts) {
	if(value.startsWith(attempt)) {
	    String rest = value.substring(attempt.length());
	    Pair<Throw, String> parsed = new Pair<Throw, String>(result, rest);
	    return Arrays.asList(parsed);
	}
		
	return tryParse(attempts);
    }

}