package com.bcit.dongmin_lab1;

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


    public void UpdateText(View v) {
        EditText myEditText = findViewById(R.id.editText_main_textField);
        TextView myTextView = findViewById(R.id.textView_main_result);
        String myString = myEditText.getText().toString().trim();
        if (myString.isEmpty()) {
            myTextView.setText(getString(R.string.main_emptyInputMessage));
            myTextView.setTextColor(Color.RED);
            return;
        }
        myTextView.setText(myString);

        switch (v.getId()) {
            case R.id.button_main_blue:
                myTextView.setTextColor(Color.BLUE);
                break;
            case R.id.button_main_yellow:
                myTextView.setTextColor(Color.YELLOW);
                break;
            default:
                myTextView.setTextColor(Color.GREEN);
        }
    }


    public void ClearText(View v) {
        TextView myTextView = findViewById(R.id.textView_main_result);
        myTextView.setText("");
    }


}