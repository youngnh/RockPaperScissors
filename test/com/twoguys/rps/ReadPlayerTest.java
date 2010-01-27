package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class ReadPlayerTest {

    @Test
    public void testReturns1ResultConsumesEntireInput() {
	Read<Player> reader = new ReadPlayer();
	String input = variableLengthString();

	List<Pair<Player, String>> result = reader.read(input);

	assertEquals(1, result.size());
	assertEquals("", result.get(0).b);
    }

    @Test
    public void testAllOfInputIsPlayerName() {
	Read<Player> reader = new ReadPlayer();
	String input = variableLengthString();

	List<Pair<Player, String>> result = reader.read(input);

	assertEquals(1, result.size());
	assertEquals(input, result.get(0).a.getName());	
    }

    private String variableLengthString() {
	Random r = new Random();
	int len = r.nextInt(1024);
	byte[] randBytes = new byte[len];
	r.nextBytes(randBytes);
	return new String(randBytes);
    }

}