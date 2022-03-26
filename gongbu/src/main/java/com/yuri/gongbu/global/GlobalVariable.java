package com.yuri.gongbu.global;

import java.util.List;
import java.util.ArrayList;

public class GlobalVariable {
    public static Integer FALSE = 0;
    public static Integer TRUE = 1;

    public static List<String> sorts =
        new ArrayList<String>() {{ add("wordHits"); add("wordLike"); }};

    public static Integer WORD_PAGE_SIZE = 6;

    public static String INVALID_ACCESS = "不正なアクセスです";   

    public static Integer COOKIE_MAX_AGE = 60 * 60 * 24;

    public static Integer POSTED_WORDS_MAX_SIZE = 12;
}