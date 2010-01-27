package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class TryParseTest {

    @Test
    public void testEmptyListReturnsEmptyParses() {
	List<Pair<String, Throw>> tries = new ArrayList<Pair<String, Throw>>();

	TryParse tryParse = new TryParse(tries);
	List<Pair<Throw, String>> result = tryParse.read("jabberwocky");

	assertEquals(0, result.size());
    }

    @Test
    public void testSingleParseWithMatchingStartReturnsGiven() {
	List<Pair<String, Throw>> tries = new ArrayList<Pair<String, Throw>>();
	Rock rock = new Rock();
	tries.add(new Pair<String, Throw>("R", rock));

	TryParse tryParse = new TryParse(tries);
	List<Pair<Throw, String>> result = tryParse.read("Rjabberwocky");

	assertEquals(1, result.size());
	assertSame(rock, result.get(0).a);
    }

    @Test
    public void testSingleParseWithMatchingReturnsUnparsedPortion() {
	List<Pair<String, Throw>> tries = new ArrayList<Pair<String, Throw>>();
	Rock rock = new Rock();
	tries.add(new Pair<String, Throw>("R", rock));

	TryParse tryParse = new TryParse(tries);
	String r = "R";
	String rest = "jabberwocky";
	List<Pair<Throw, String>> result = tryParse.read(r + rest);

	assertEquals(1, result.size());
	assertEquals(rest, result.get(0).b);
    }

    @Test
    public void testMultipleParsesMatchesOnlyCorrectPortion() {
	List<Pair<String, Throw>> tries = new ArrayList<Pair<String, Throw>>();
	tries.add(new Pair<String, Throw>("A", new Rock()));
	tries.add(new Pair<String, Throw>("BB", new Paper()));
	tries.add(new Pair<String, Throw>("CCC", new Scissors()));

	String start = "BB";
	String rest = "jabberwocky";

	TryParse tryParse = new TryParse(tries);
	List<Pair<Throw, String>> result = tryParse.read(start + rest);

	assertEquals(1, result.size());
	assertEquals(new Paper(), result.get(0).a);
	assertEquals(rest, result.get(0).b);
    }

    @Test
    public void testMultipleParsesMatchingMultipleStringsReturnMultipleResults() {
	List<Pair<String, Throw>> tries = new ArrayList<Pair<String, Throw>>();
	tries.add(new Pair<String, Throw>("A", new Rock()));
	tries.add(new Pair<String, Throw>("Ab", new Paper()));
	tries.add(new Pair<String, Throw>("Abracadabra", new Scissors()));

	TryParse tryParse = new TryParse(tries);
	String rest = "jabberwocky";
	List<Pair<Throw, String>> result = tryParse.read("Abracadabra" + rest);

	assertEquals(3, result.size());

	assertEquals(new Rock(), result.get(0).a);
	assertEquals("bracadabra" + rest, result.get(0).b);

	assertEquals(new Paper(), result.get(1).a);
	assertEquals("racadabra" + rest, result.get(1).b);
	
	assertEquals(new Scissors(), result.get(2).a);
	assertEquals(rest, result.get(2).b);
    }

    @Test
    public void testMultipleParsesWithNoMatchesReturnsNoResults() {
	List<Pair<String, Throw>> tries = new ArrayList<Pair<String, Throw>>();
	tries.add(new Pair<String, Throw>("A", new Rock()));
	tries.add(new Pair<String, Throw>("Ab", new Paper()));
	tries.add(new Pair<String, Throw>("Abracadabra", new Scissors()));

	TryParse tryParse = new TryParse(tries);
	List<Pair<Throw, String>> result = tryParse.read("all along the watchtower");

	assertEquals(0, result.size());
    }
}