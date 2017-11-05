package com.example.lipa.englishforchildren.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipa on 10.10.17.
 */

public class SQLiteEnglishWordsDBHelper extends SQLiteOpenHelper {
    public static String DB_PATH;
    public static final String DATABASE_NAME = "english_words_db.db";
    public static final int DATABASE_VERSION = 1;

    static final String COLUMN_ID = "_id";
    static final String COLUMN_NAME = "word";

    private Context mContext;
//https://metanit.com/java/android/14.3.php

    public SQLiteEnglishWordsDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteEnglishWordsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DB_PATH = context.getFilesDir().getPath() + DATABASE_NAME;
        mContext = context;
        Log.d("DBHelper", "constructor");
        // createDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("SQLiteEnglishWordsDB", "onCreate");

//        String SQL_CREATE_TABLE =
//                "CREATE TABLE " + EnglishWordContract.Word.TABLE_NAME + " ( "
//                        + EnglishWordContract.Word._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                        + EnglishWordContract.Word.WORD + " TEXT NOT NULL, "
//                        + EnglishWordContract.Word.TRANSLATE + " TEXT NOT NULL, "
//                        + EnglishWordContract.Word.RESOURCE + " INTEGER NOT NULL );";
//        db.execSQL(SQL_CREATE_TABLE);
//       Log.d("SQLcreateTable", SQL_CREATE_TABLE);
        // createDatabase();

    }

    public void initDB() {
        //Копирование данных из готовой ДБ, лежащей в assets в DB_PATH
        Log.d("create DB", "start");
        File file = new File(DB_PATH);
        InputStream inputStream = null;
        OutputStream outputStream = null;

        if (!file.exists()) {
            Log.d("file", "doesnt exist");
            this.getReadableDatabase();
            try {
                inputStream = mContext.getAssets().open(DATABASE_NAME);
                outputStream = new FileOutputStream(DB_PATH);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                    Log.d("read", "from inputStream");
                }

                outputStream.flush();
                inputStream.close();
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("file", " exist");
        }
        Log.d("create DB", "finish");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public long insertEnglishWord(EnglishWord englishWord) {
//        Log.d("insertEnglishWord", "start" + getDatabaseName());
//        long id = 0;
//
//        SQLiteDatabase database = getWritableDatabase();
//        Log.d("getWritableDatabase", "start");
//
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(EnglishWordContract.Word.WORD, englishWord.getWord());
//        contentValues.put(EnglishWordContract.Word.TRANSLATE, englishWord.getTranslate());
//        contentValues.put(EnglishWordContract.Word.RESOURCE, englishWord.getResourceID());
//
//        database.beginTransaction();
//        database.insert(EnglishWordContract.Word.TABLE_NAME, null, contentValues);
//        database.setTransactionSuccessful();
//        database.endTransaction();
//
//        database.close();
//        return id;
//    }

    public List<EnglishWord> readAllEnglishWords() {
        Log.d("readAllEnglishWords", "start");
        List<EnglishWord> englishWordList = new ArrayList<>();

        SQLiteDatabase database = open();

        Cursor cursor = database.query(EnglishWordContract.Word.TABLE_NAME, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EnglishWord newEnglishWord = new EnglishWord();
            String word = cursor.getString(cursor.getColumnIndex(EnglishWordContract.Word.WORD));
            Log.d("words", word);
//            String translate = cursor.getString(cursor.getColumnIndex(EnglishWordContract.Word.TRANSLATE));
//            int resource = cursor.getInt(cursor.getColumnIndex(EnglishWordContract.Word.RESOURCE));
//            Log.d("ew", word + " " + translate + " ");
//            newEnglishWord.setWord(word);
//            newEnglishWord.setTranslate(translate);
//            newEnglishWord.setResourceID(resource);
//            englishWordList.add(newEnglishWord);
            cursor.moveToNext();
        }
        database.close();
        return englishWordList;
    }


    public SQLiteDatabase open() {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}


