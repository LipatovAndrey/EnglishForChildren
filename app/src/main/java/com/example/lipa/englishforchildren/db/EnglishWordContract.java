package com.example.lipa.englishforchildren.db;

import android.provider.BaseColumns;

/**
 * Created by Андрей on 10.10.2017.
 */

public class EnglishWordContract {
    public static class Word implements BaseColumns {

        public static final String TABLE_NAME = "english_words_table";
        public static final String WORD = "word";
        public static final String GROUP = "group";
        public static final String GROUP_IMAGE_ID = "idGroupImage";
        public static final String TRANSLATE = "translateWord";
        public static final String RESOURCE = "idImage";
    }


}
