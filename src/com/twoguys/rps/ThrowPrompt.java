package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;

public class ThrowPrompt extends PromptForFrom<Throw> {

    public ThrowPrompt(LineNumberReader in, PrintStream out) {
	super(in, new PutStrTo(out, "[R]ock, [P]aper, or [S]cissors? "), new com.twoguys.util.Reader<Throw>(new ReadThrow()));
    }

}