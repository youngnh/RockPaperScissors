package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class FirstToTest extends FirstToAbstractTest {

    public WinLogic getLogic(int to) {
	return new FirstTo(to);
    }
}