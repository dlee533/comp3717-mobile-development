package com.bcit.lec3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Log.d("LIFECYCLE INFO", "CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLE INFO", "START");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LIFECYCLE INFO", "STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFECYCLE INFO", "DESTROY");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLE INFO", "PAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLE INFO", "RESUME");
    }
}