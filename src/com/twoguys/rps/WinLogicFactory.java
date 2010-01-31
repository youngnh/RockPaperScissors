package com.twoguys.rps;

import static org.hamcrest.Matchers.*;

import com.twoguys.util.*;
import java.util.*;
import org.hamcrest.*;

public class WinLogicFactory {

    private static List<Pair<Matcher, WinLogicInternalFactory>> matchers;

    static {
	matchers = new ArrayList<Pair<Matcher, WinLogicInternalFactory>>();
	matchers.add(new Pair<Matcher, WinLogicInternalFactory>(hasItems(is("-to"), anything(), is("-by"), anything()), winBy()));
	matchers.add(new Pair<Matcher, WinLogicInternalFactory>(hasItems(is("-to"), anything()), firstTo()));
	matchers.add(new Pair<Matcher, WinLogicInternalFactory>(hasItems(is("-bestof"), anything()), bestOf()));
    }

    public static Either<String, WinLogic> create(String... args) {
	if(args.length == 0) {
	    return new Right(new FirstTo(1));
	}

	for(Pair<Matcher, WinLogicInternalFactory> pair : matchers) {
	    Matcher matcher = pair.a;
	    WinLogicInternalFactory factory = pair.b;
	    if(matcher.matches(Arrays.asList(args))) {
		return factory.create(args);
	    }
	}
	return new Left("boo");
    }

    public static WinLogicInternalFactory firstTo() {
	return new WinLogicInternalFactory() {
	    public Either<String, WinLogic> create(String... args) {
		try {
		    int to = Integer.parseInt(args[1]);
		    return new Right(new FirstTo(to));
		} catch(NumberFormatException e) {
		    return new Left("'" + args[1] + "' not a valid integer");
		} catch(IllegalArgumentException e) {
		    return new Left(e.getMessage());
		}
	    }
	};
    }

    public static WinLogicInternalFactory winBy() {
	return new WinLogicInternalFactory() {
	    public Either<String, WinLogic> create(String... args) {
		int to, by;
		try {
		    to = Integer.parseInt(args[1]);
		} catch(NumberFormatException e) {
		    return new Left("'" + args[1] + "' not a valid integer");
		}
		try {
		    by = Integer.parseInt(args[3]);
		    return new Right(new WinBy(to, by));
		} catch(NumberFormatException e) {
		    return new Left("'" + args[3] + "' not a valid integer");
		} catch(IllegalArgumentException e) {
		    return new Left(e.getMessage());
		}
	    }
	};
    }

    public static WinLogicInternalFactory bestOf() {
	return new WinLogicInternalFactory() {
	    public Either<String, WinLogic> create(String... args) {
		try {
		    int of = Integer.parseInt(args[1]);
		    return new Right(new BestOf(of));
		} catch(NumberFormatException e) {
		    return new Left("'" + args[1] + "' not a valid integer");
		} catch(IllegalArgumentException e) {
		    return new Left(e.getMessage());
		}
	    }
	};
    }

    interface WinLogicInternalFactory {
	Either<String, WinLogic> create(String... args);
    }
}