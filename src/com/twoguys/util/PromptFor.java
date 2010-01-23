package com.twoguys.util;

import java.io.*;
import java.text.*;

public interface PromptFor<T> {

    public T prompt() throws IOException, ParseException;
}