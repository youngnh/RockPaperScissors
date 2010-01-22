package com.twoguys.util;

import java.util.*;

public interface Readable<T> {

    List<Pair<T, String>> read(String s);

}