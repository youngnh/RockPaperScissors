package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public interface Read<T> {

    List<Pair<T, String>> read(String str);
}