package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;

public class PlayerPrompt extends PromptForFrom<Player> {

    public PlayerPrompt(LineNumberReader in, PrintStream out, int n) {
	super(in, new PutStrTo(out, "Player " + n + " Name: "), new com.twoguys.util.Reader<Player>(new ReadPlayer()));
    }

}