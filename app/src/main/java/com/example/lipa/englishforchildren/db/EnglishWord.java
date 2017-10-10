package com.example.lipa.englishforchildren.db;

/**
 * Created by Андрей on 10.10.2017.
 */

class EnglishWord {

    private String mWord;
    private String mTranslate;
    private int mResourceID;

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public String getTranslate() {
        return mTranslate;
    }

    public void setTranslate(String translate) {
        mTranslate = translate;
    }

    public int getResourceID() {
        return mResourceID;
    }

    public void setResourceID(int resourceID) {
        mResourceID = resourceID;
    }
}
