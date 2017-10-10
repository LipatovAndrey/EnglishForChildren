package com.example.lipa.englishforchildren.db;

import android.provider.BaseColumns;

/**
 * Created by Андрей on 10.10.2017.
 */

public class EnglishWordContract {
    public static class Word implements BaseColumns{
        public static final String WORD = "english_word";
        public static final String TRANSLATE = "translate_word";
        public static final String RESOURCE = "id_resource";
    }
}
