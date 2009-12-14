
import com.twoguys.rps.*;
import java.io.*;

public class RockPaperScissors {

    public static void main(String[] args) throws Exception {
	Game game;
	if(args[0] == "-bestof") {
	    int x = Integer.parseInt(args[1]);
	    game = new BestOf(x);
	} else if(args[0] == "-to") {
	    int x = Integer.parseInt(args[1]);
	    game = new FirstTo(x);
	} else if(args[0] == "-to" && args[2] == "-by") {
	    int x = Integer.parseInt(args[1]);
	    int y = Integer.parseInt(args[3]);
	    game = new WinBy(x, y);
	} else {
	    System.exit(1);
	    return;
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