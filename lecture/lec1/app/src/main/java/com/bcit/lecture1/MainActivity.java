package com.bcit.lecture1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DoSomething(View v) {
        // do something
        TextView myTextView = findViewById(R.id.textView_main);
        EditText myEditText = findViewById(R.id.editText_main);
        String str = myEditText.getText().toString();
        myTextView.setText(str);
    }

    public void ChangeText(View v) {
        // Dongmin (Mina) Lee
        TextView mTextView = findViewById(R.id.textView_main);
        mTextView.setText("Learning is fun!");
        mTextView.setTextColor(Color.BLUE);
    }
}