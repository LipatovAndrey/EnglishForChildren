package com.example.lipa.englishforchildren;

import android.app.Application;
import android.util.Log;

import com.example.lipa.englishforchildren.db.SQLiteEnglishWordsDBHelper;

/**
 * Created by lipa on 21.10.17.
 */

public class MyApplication extends Application{
    public SQLiteEnglishWordsDBHelper mSqLiteEnglishWordsDBHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Application", "onCreate");
        mSqLiteEnglishWordsDBHelper = new SQLiteEnglishWordsDBHelper(this);
    }
}
