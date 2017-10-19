package com.example.lipa.englishforchildren.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lipa on 10.10.17.
 */

public class SQLiteEnglishWordsDBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "english_words_db";
    public static final int DATABASE_VERSION = 2;


    public SQLiteEnglishWordsDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteEnglishWordsDBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DBHelper", "constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("onCreateDB", db.getPath());
        String SQL_CREATE_TABLE =
                "CREATE TABLE " + EnglishWordContract.Word.TABLE_NAME + " ( "
                        + EnglishWordContract.Word._ID + " INTEGER PRYMARY KEY AUTOINCREMENT, "
                        + EnglishWordContract.Word.WORD + " TEXT NOT NULL, "
                        + EnglishWordContract.Word.TRANSLATE + " TEXT NOT NULL, "
                        + EnglishWordContract.Word.RESOURCE + " INTEGER NOT NULL );";

        Log.d(SQL_CREATE_TABLE, " ");
        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertEnglishWord(EnglishWord englishWord){
        Log.d("insertEnglishWord", "start" + getDatabaseName());
        long id=0;
        SQLiteDatabase database = getWritableDatabase();
        Log.d("getWritableDatabase", "start");

        ContentValues contentValues = new ContentValues();
        contentValues.put(EnglishWordContract.Word.WORD, englishWord.getWord());
        contentValues.put(EnglishWordContract.Word.TRANSLATE, englishWord.getTranslate());
        //contentValues.put(EnglishWordContract.Word.RESOURCE, englishWord.getResourceID());



        database.beginTransaction();
        database.insert(EnglishWordContract.Word.TABLE_NAME, null, contentValues);
        database.endTransaction();

        database.close();
        return id;
    }

    public List<EnglishWord> readAllEnglishWords(){
        Log.d("readAllEnglishWords", "start");
        List<EnglishWord> englishWordList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(EnglishWordContract.Word.TABLE_NAME, null, null, null, null, null,null);
        cursor.moveToFirst();
        while (!cursor.isLast()){
            EnglishWord newEnglishWord = new EnglishWord();
            String word = cursor.getString(cursor.getColumnIndex(EnglishWordContract.Word.WORD));
            String translate = cursor.getString(cursor.getColumnIndex(EnglishWordContract.Word.TRANSLATE));
            int resource = cursor.getInt(cursor.getColumnIndex(EnglishWordContract.Word.RESOURCE));
            newEnglishWord.setWord(word);
            newEnglishWord.setTranslate(translate);
            newEnglishWord.setResourceID(resource);
            englishWordList.add(newEnglishWord);
            cursor.moveToNext();
        }
        database.close();
        return englishWordList;
    }
    public void initDataBase(){
        Log.d("initDataBase", "start");
        EnglishWord englishWord = new EnglishWord();
        englishWord.setResourceID(1);
        englishWord.setWord("first");
        englishWord.setTranslate("первый");
        insertEnglishWord(englishWord);
        englishWord.setResourceID(2);
        englishWord.setWord("second");
        englishWord.setTranslate("второй");
        insertEnglishWord(englishWord);
    }
}


