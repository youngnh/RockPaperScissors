
import com.twoguys.rps.*;
import java.io.*;

public class RockPaperScissors {

    public static void main(String[] args) throws Exception {
	Game game;
	if(args[0] == "-bestof") {
	    game = new BestOf(args[1]);
	}
	if(args[0] == "-to") {
	    game = new FirstTo(args[1]);
	}
	if(args[0] == "-to" && args[2] == "-by") {
	    game = new WinBy(args[1], args[3]);
	}

	Reader in = new InputStreamReader(System.in);
	Writer out = new OutputStreamWriter(System.out);

	Player p1 = new InteractivePlayer(in, out, 1);
	Player p2 = new InteractivePlayer(in, out, 2);

	game.setPlayer1(p1);
	game.setPlayer2(p2);

	Player winner = game.play();

	winner.notifyWinner();
    }
}