package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;

public class PlayerPrompt extends Prompter<Player> {

    public PlayerPrompt(java.io.Reader in, Writer out, int n) {
	super(in, out, "Player " + n + " Name: ", new PlayerReader());
    }

}