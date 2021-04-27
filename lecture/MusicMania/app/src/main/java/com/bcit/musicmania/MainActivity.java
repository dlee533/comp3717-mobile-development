package com.bcit.musicmania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private RecyclerView rv;
    private MusicFunAdapter adapter;
    private MusicFunService musicFunService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recycler_main);
    }

    public void onClickShuffle(View v)
    {
        Intent intent = new Intent(this, MusicFunService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    public void onClickPause(View v)
    {
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MusicFunService.MusicFunBinder b = (MusicFunService.MusicFunBinder) iBinder;
        musicFunService = b.getService();

        musicFunService.setOnMusicFunListener(new OnMusicFunListener() {
            @Override
            public void onNewsong() {
                adapter.notifyDataSetChanged();
            }
        });

        Intent intent = new Intent(this, MusicFunService.class);
        startService(intent);

        adapter = new MusicFunAdapter(musicFunService.getSongList());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        musicFunService = null;
    }

    @Override
    protected void onDestroy() {
        //Dongmin Lee
        super.onDestroy();
        Intent intent = new Intent(this, MusicFunService.class);
        stopService(intent);
    }

}