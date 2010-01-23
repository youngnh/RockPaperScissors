package com.twoguys.rps;

import static org.hamcrest.Matchers.*;

import org.hamcrest.*;

public class WinLogicFactory {

    public static Either<String, WinLogic> createWinLogic(String... args) {
	Matcher<Iterable<Object>> firstToWinBy = hasItems(is("-to"), anything(), is("-by"), anything());
	Matcher<Iterable<Object>> bestOf = hasItems(is("-to"), anything());
	Matcher<Iterable<Object>> firstTo = hasItems(is("-to"), anything());
// 	Matcher<Iterable<Object>> emptyArgs = hasItems(nothing());

	if(firstToWinBy.matches(args)) {
	    return createFirstToWinBy(args[1], args[3]);
	} else if(bestOf.matches(args)) {
	    return createBestOf(args[1]);
	} else if(firstTo.matches(args)) {
	    return createFirstTo(args[1]);
// 	} else if(emptyArgs.matches(args)) {
// 	    return createFirstTo("1");
	} else {
	    return usage();
	}
    }

    public static Either<String, WinLogic> createFirstToWinBy(String toString, String byString) {
	int to = Integer.parseInt(toString);
	int by = Integer.parseInt(byString);

	WinLogic logic = new FirstToWinBy(to, by);
	return new Right(logic);
    }

    public static Either<String, WinLogic> createBestOf(String xString) {
	int x = Integer.parseInt(xString);

	WinLogic logic = new BestOf(x);
	return new Right(logic);
    }

    public static Either<String, WinLogic> createFirstTo(String xString) {
	int x = Integer.parseInt(xString);

	WinLogic logic = new FirstTo(x);
	return new Right(logic);
    }

    public static Either<String, WinLogic> usage() {
	String usage = 
	    "usage:\tRockPaperScissors -to to\n" +
	    "      \tRockPaperScissors -bestof x\n" +
	    "      \tRockPaperScissors -to to -by by";
	return new Left(usage);
    }

}