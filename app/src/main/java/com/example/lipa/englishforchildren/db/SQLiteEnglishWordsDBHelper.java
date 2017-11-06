package com.example.lipa.englishforchildren.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    }

    public void initDB() {
        //Копирование данных из готовой ДБ, лежащей в assets в DB_PATH
        Log.d("init DB", "start");
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
        Log.d("init DB", "finish");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public List<EnglishWord> readAllEnglishWords() {
        Log.d("readAllEnglishWords", "start");
        List<EnglishWord> englishWordList = new ArrayList<>();

        SQLiteDatabase database = open();
        Cursor cursor = database.query(EnglishWordContract.Word.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EnglishWord newEnglishWord = new EnglishWord();
            String word = cursor.getString(cursor.getColumnIndex(EnglishWordContract.Word.WORD));
            String translate = cursor.getString(cursor.getColumnIndex(EnglishWordContract.Word.TRANSLATE));
            Log.d("words", word + " " + translate);
            cursor.moveToNext();
        }
        database.close();
        return englishWordList;
    }


    public Set<String> readGroups() {
        Log.d("readGroups", "start");
        SQLiteDatabase database = open();
        Set<String> groups = new HashSet<>();
        Cursor cursor = database.query(EnglishWordContract.Word.TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            groups.add(cursor.getString(cursor.getColumnIndex(EnglishWordContract.Word.GROUP)));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return groups;
    }


    public void loadGroupPNGImage(String fileName, ImageView imageView) {
        InputStream inputStream = null;
        try {
            inputStream = mContext.getAssets().open(fileName + ".png");
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public SQLiteDatabase open() {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}


