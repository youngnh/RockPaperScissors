
import com.twoguys.rps.*;
import java.io.*;

public class RockPaperScissors {

    public static void main(String[] args) throws Exception {
	Reader in = new InputStreamReader(System.in);
	Writer out = new OutputStreamWriter(System.out);

	Player p1 = new InteractivePlayer(in, out, 1);
	Player p2 = new InteractivePlayer(in, out, 2);

	Game game = new Game(p1, p2);
	Player winner = game.play();
	winner.notifyWinner();
    }
}