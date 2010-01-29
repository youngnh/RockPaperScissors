package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class BestOfTest extends FirstToAbstractTest {

    public int getTo() {
	int to = new Random().nextInt(100);
	if(to % 2 == 0) {
	    to += 1;
	}
	return to;
    }

    public WinLogic getLogic(int to) {
	return new BestOf(to * 2 - 1);
    }
}