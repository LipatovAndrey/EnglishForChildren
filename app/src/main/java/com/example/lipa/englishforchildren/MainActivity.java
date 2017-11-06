package com.example.lipa.englishforchildren;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lipa.englishforchildren.db.SQLiteEnglishWordsDBHelper;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GridLayout mMyGridLayout;
    SQLiteEnglishWordsDBHelper mSqLiteEnglishWordsDBHelper;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflateElements();

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, v.toString(), Toast.LENGTH_SHORT).show();
        Log.d("click", "click");
        ((MyApplication) getApplication()).mSqLiteEnglishWordsDBHelper.readAllEnglishWords();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void inflateElements() {
        Set<String> groups = ((MyApplication) getApplication()).mSqLiteEnglishWordsDBHelper.readGroups();
        mMyGridLayout = (GridLayout) findViewById(R.id.MyGridLayout);

        for (String s : groups
                ) {
            ImageView imageView = new ImageView(this);
            imageView.setBackground(getResources().getDrawable(R.drawable.my_image_button, null));
            ((MyApplication) getApplication()).mSqLiteEnglishWordsDBHelper.loadGroupPNGImage(s, imageView);
            imageView.setOnClickListener(this);
            mMyGridLayout.addView(imageView);
        }

    }
}
