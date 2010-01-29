package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;
import org.mockito.*;

public class GameTest {

    @Test
    public void testWinnerGivenByLogicIsReturned() {
	WinLogic logic = mock(WinLogic.class);
	Player player1 = new Player("Hannibal");
	when(logic.determineWinner(any(Pair.class), any(Pair.class))).thenReturn(new Just(player1));
	Pair<Player, Iterator<Throw>> p1 = mock(Pair.class);
	Pair<Player, Iterator<Throw>> p2 = mock(Pair.class);
	
	Game game = new Game(logic, p1, p2);
	Player winner = game.play();

	assertSame(player1, winner);
    }

    @Test
    public void testFirstDetermineWinnerBothPlayersHaveNoScore() {
	WinLogic logic = mock(WinLogic.class);
	Player player1 = new Player("Hannibal");
	when(logic.determineWinner(any(Pair.class), any(Pair.class))).thenReturn(new Just(player1));

	Pair<Player, Iterator<Throw>> p1 = mock(Pair.class);
	Pair<Player, Iterator<Throw>> p2 = mock(Pair.class);
	
	Game game = new Game(logic, p1, p2);
	game.play();

	verify(logic).determineWinner(argThat(hasScoreOf(0)), argThat(hasScoreOf(0)));
    }

    @Test
    public void testNoWinnerPromptsForThrow() {
	WinLogic logic = mock(WinLogic.class);

	Player player1 = new Player("Hannibal");
	Player player2 = new Player("Ghandi");

	when(logic.determineWinner(any(Pair.class), any(Pair.class)))
	    .thenReturn(new Nothing())
	    .thenReturn(new Just(player1));

	Iterator<Throw> mockIter1 = mock(Iterator.class);
	Iterator<Throw> mockIter2 = mock(Iterator.class);

	when(mockIter1.hasNext()).thenReturn(true);
	when(mockIter1.next()).thenReturn(new Rock());
	when(mockIter2.hasNext()).thenReturn(true);
	when(mockIter2.next()).thenReturn(new Paper());

	InOrder inOrder1 = inOrder(mockIter1);
	InOrder inOrder2 = inOrder(mockIter2);

	Pair<Player, Iterator<Throw>> p1 = new Pair<Player, Iterator<Throw>>(player1, mockIter1);
	Pair<Player, Iterator<Throw>> p2 = new Pair<Player, Iterator<Throw>>(player2, mockIter2);
	
	Game game = new Game(logic, p1, p2);
	game.play();

	inOrder1.verify(mockIter1).hasNext();
	inOrder1.verify(mockIter1).next();
	inOrder2.verify(mockIter2).hasNext();
	inOrder2.verify(mockIter2).next();
    }

    @Test
    public void testThrowWinnersScoreIsIncremented() {
	WinLogic logic = mock(WinLogic.class);

	Player player1 = new Player("Hannibal");
	Player player2 = new Player("Ghandi");

	when(logic.determineWinner(any(Pair.class), any(Pair.class)))
	    .thenReturn(new Nothing())
	    .thenReturn(new Just(player1));

	Iterator<Throw> mockIter1 = mock(Iterator.class);
	Iterator<Throw> mockIter2 = mock(Iterator.class);

	when(mockIter1.hasNext()).thenReturn(true);
	when(mockIter1.next()).thenReturn(new Rock());
	when(mockIter2.hasNext()).thenReturn(true);
	when(mockIter2.next()).thenReturn(new Paper());

	InOrder inOrder = inOrder(logic);

	Pair<Player, Iterator<Throw>> p1 = new Pair<Player, Iterator<Throw>>(player1, mockIter1);
	Pair<Player, Iterator<Throw>> p2 = new Pair<Player, Iterator<Throw>>(player2, mockIter2);
	
	Game game = new Game(logic, p1, p2);
	game.play();

	inOrder.verify(logic, times(1)).determineWinner(argThat(hasScoreOf(0)), argThat(hasScoreOf(0)));
	inOrder.verify(logic, times(1)).determineWinner(argThat(hasScoreOf(0)), argThat(hasScoreOf(1)));
    }

    @Test
    public void testThrowTieNoScoreIncrement() {
	WinLogic logic = mock(WinLogic.class);

	Player player1 = new Player("Hannibal");
	Player player2 = new Player("Ghandi");

	when(logic.determineWinner(any(Pair.class), any(Pair.class)))
	    .thenReturn(new Nothing())
	    .thenReturn(new Just(player1));

	Iterator<Throw> mockIter1 = mock(Iterator.class);
	Iterator<Throw> mockIter2 = mock(Iterator.class);

	when(mockIter1.hasNext()).thenReturn(true);
	when(mockIter1.next()).thenReturn(new Rock());
	when(mockIter2.hasNext()).thenReturn(true);
	when(mockIter2.next()).thenReturn(new Rock());

	Pair<Player, Iterator<Throw>> p1 = new Pair<Player, Iterator<Throw>>(player1, mockIter1);
	Pair<Player, Iterator<Throw>> p2 = new Pair<Player, Iterator<Throw>>(player2, mockIter2);
	
	Game game = new Game(logic, p1, p2);
	game.play();

	verify(logic, times(2)).determineWinner(argThat(hasScoreOf(0)), argThat(hasScoreOf(0)));
    }

    public static ArgumentMatcher<Pair<Player, Integer>> hasScoreOf(final int x) {
	return new ArgumentMatcher<Pair<Player, Integer>>() {
	    public boolean matches(Object pair) {
		return ((Pair<Player, Integer>) pair).b == x;
	    }
	};
    }

}