package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class ReadThrow extends TryParse<Throw> {

    public ReadThrow() {
	super(Arrays.asList(new Pair<String, Throw>("R", new Rock()),
			    new Pair<String, Throw>("P", new Paper()),
			    new Pair<String, Throw>("S", new Scissors())));
    }

}