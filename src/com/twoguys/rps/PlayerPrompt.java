package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;

public class PlayerPrompt extends Prompt<Player> {

    public PlayerPrompt(LineNumberReader in, Writer out, int n) {
	super(in, new Notify(out, "Player " + n + " Name: "), new com.twoguys.util.Reader<Player>(new ReadPlayer()));
    }

}