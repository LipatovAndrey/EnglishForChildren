package com.example.lipa.englishforchildren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GridLayout mMyGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyGridLayout = (GridLayout) findViewById(R.id.MyGridLayout);
        for (int i = 0; i <10 ; i++) {
            Button button = new Button(this);
            button.setText("кнопка");
            button.setOnClickListener(this);
            mMyGridLayout.addView(button);
        }



    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, v.toString(), Toast.LENGTH_SHORT).show();
    }
}
