package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;

public class ThrowPrompt extends Prompter<Throw> {

    public ThrowPrompt(java.io.Reader in, Writer out) {
	super(in, out, "[R]ock, [P]aper, or [S]cissors? ", new ThrowReader());
    }

}