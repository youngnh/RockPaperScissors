import com.twoguys.rps.*;
import com.twoguys.util.*;

public class RockPaperScissors {

    public static void main(String[] args) throws Exception {
	Either<String, WinLogic> logic = WinLogicFactory.createWinLogic(args);
	if(logic.getClass() == Left.class) {
	    main((Left) logic);
	} else {
	    main((Right) logic);
	}
    }

    public static void main(Left<String> error) {
	String usage = error.left();
	System.out.println(usage);
	System.exit(1);
    }

    public static void main(Right<WinLogic> logic) throws Exception {
	PlayGame game = new PlayGame(logic.right());
	game.play();
    }

}