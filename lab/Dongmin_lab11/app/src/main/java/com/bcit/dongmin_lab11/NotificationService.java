package com.bcit.dongmin_lab11;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.core.app.NotificationCompat;

public class NotificationService extends Service {

    Handler handler;
    private String randomFact;
    private final String SERVICE_URL = "https://useless-facts.sameerkumar.website/api";
    private final NotificationBinder mBinder = new NotificationBinder();
    private final String NOTIFICATION_CHANNEL_ID = "NotificationServiceChannel";
    private PendingIntent actionPendingIntent;
    private NotificationManager notificationManager;
    private int id;

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        id = 1;
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                "Random Fact Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);

        Intent notificationIntent = new Intent(NotificationService.this, MainActivity.class);
        actionPendingIntent = PendingIntent.getActivity(NotificationService.this, 0, notificationIntent, 0);

        handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, createNotification());
            }
        });
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        handler.removeCallbacksAndMessages(null);
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
        notificationManager.cancelAll();
    }

    public class NotificationBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }

    private int createNotification() {
        new GetFact().execute();

        return 10000;
    }

    private class GetFact extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler httpHandler = new HttpHandler();

            randomFact = httpHandler.makeServiceCall(SERVICE_URL);
            randomFact = randomFact.substring(9, randomFact.lastIndexOf("\""));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            System.out.println(randomFact);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationService.this, NOTIFICATION_CHANNEL_ID)
                    .setContentTitle("Random Fact")
                    .setContentText(randomFact)
                    .setSmallIcon(R.drawable.emoji1)
                    .setContentIntent(actionPendingIntent)
                    .setAutoCancel(true);
            notificationManager.notify(id++, builder.build());
//            startForeground(1, notification);
        }
    }
}