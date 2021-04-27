package com.bcit.musicmania;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MusicFunService extends Service {

    MediaPlayer mediaPlayer;
    Handler handler;
    private final MusicFunBinder mBinder = new MusicFunBinder();
    private OnMusicFunListener onMusicFunListener;

    public static final String NOTIFICATION_CHANNEL_ID = "MusicFunServiceChannel";

    public void setOnMusicFunListener(OnMusicFunListener onMusicFunListener) {
        this.onMusicFunListener = onMusicFunListener;
    }


    private List<String> playedSongList = new ArrayList<>();
    public List<String> getSongList() {
        return playedSongList;
    }


    public MusicFunService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotification();
        shuffleSongs();
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        handler.removeCallbacksAndMessages(null);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private int prepareNextSong() {
        Random random = new Random();

        List<String> input = Arrays.asList(getResources().getStringArray(R.array.Songs));
        String nextRandomSong = input.get(random.nextInt(input.size()));

        int resourceIdString = this.getResources().getIdentifier(nextRandomSong, "string", this.getPackageName());
        playedSongList.add(0, getResources().getString(resourceIdString));
        onMusicFunListener.onNewsong();

        int resourceIdRaw = this.getResources().getIdentifier(nextRandomSong, "raw", this.getPackageName());

        mediaPlayer = MediaPlayer.create(this, resourceIdRaw);
        int songLength = mediaPlayer.getDuration();
        mediaPlayer.start();

        return songLength;
    }

    private void shuffleSongs() {
        handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, prepareNextSong());
            }
        });
    }

    public class MusicFunBinder extends Binder {
        MusicFunService getService() {
            return MusicFunService.this;
        }
    }

    private void createNotification() {
        // create notification channel
        NotificationChannel serviceChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                "Music Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);

        // create notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("MusicFun Service")
                .setContentText("MusicFun is playing music")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentIntent(actionPendingIntent)
                .build();

        startForeground(1, notification);
    }

}