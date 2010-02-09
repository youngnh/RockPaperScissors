package com.twoguys.util;

import java.io.*;

public class PromptFor<T> extends PromptForFrom<T> {

    public PromptFor(Print notify, Reader<T> reader) {
	super(new LineNumberReader(new InputStreamReader(System.in)), notify, reader);
    }

}