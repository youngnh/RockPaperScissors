package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;

public class ThrowPrompt extends Prompt<Throw> {

    public ThrowPrompt(java.io.Reader in, Writer out) {
	super(in, new Notify(out, "[R]ock, [P]aper, or [S]cissors? "), new com.twoguys.util.Reader<Throw>(new ReadThrow()));
    }

}