package com.bcit.lec3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Log.d("LIFECYCLE MAIN", "CREATE");
    }

    public void GoToInfo(View v)
    {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void GetInputText(View v)
    {
        EditText editText = findViewById(R.id.editText_main);
        str = editText.getText().toString();
    }

    public void UpdateTextView(View v)
    {
        TextView textView = findViewById(R.id.textView_main);
        if (str == null || str.length() == 0)
        {
            textView.setText("The string is null");
        }
        else
        {
            textView.setText(str);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE MAIN", "START");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFECYCLE MAIN", "STOP");
        // Dongmin Lee
        // states an activity can be in during its lifetime (create, start, resume, pause, stop, destroy)
        // create, start, and resume is almost instantaneous, when the activity is interrupted it goes to
        // pause state then to either resume or stop, when the activity is killed(ending app/exit from non main activity), the destroy
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFECYCLE MAIN", "DESTROY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLE MAIN", "PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE MAIN", "RESUME");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("somekey", str);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        str = savedInstanceState.getString("somekey", "");
    }


}