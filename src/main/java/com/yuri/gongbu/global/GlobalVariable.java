package com.yuri.gongbu.global;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariable {
    public static Integer FALSE = 0;
    public static Integer TRUE = 1;

    public static List<String> sorts =
            new ArrayList<String>() {
                {
                    add("wordHits");
                    add("wordLike");
                }
            };

    public static Integer WORD_PAGE_SIZE = 6;

    public static String INVALID_ACCESS = "不正なアクセスです";

    public static Integer COOKIE_MAX_AGE = 60 * 60 * 24;

    public static Integer POSTED_WORDS_MAX_SIZE = 12;

    public static Integer MY_PAGE_WORD_PAGE_SIZE = 10;

    public static String[] PATH_FOR_MEMBER = new String[]{"/word/add", "/word/edit/**", "/word/delete/**", "/word/like/**"};

    public static String[] PATH_FOR_ALL = new String[]{"/", "/about", "/word/list/**", "/word/detail/**", "/word/random", "/loginPage", "/error", "/health", "/login/**"};

    public static String[] PATH_FOR_STATIC_RESOURCE = new String[]{"/css/**", "/image/**", "/js/**", "/*.html"};

    public static final int WORD_NAME_MAX_LENGTH = 50;

    public static final int WORD_PRONUNCIATION_MAX_LENGTH = 100;

    public static final int WORD_MEANING_MAX_LENGTH = 255;

    public static final int WORD_EXAMPLE_MAX_LENGTH = 500;

    private GlobalVariable() {
    }
}
