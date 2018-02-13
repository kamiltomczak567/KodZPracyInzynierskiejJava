package com.example.kamil.my_application;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by K on 2016-02-27.
 */
public class RingtonePlayintService extends Service {

    MediaPlayer media_song;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");

        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!this.isRunning && startId == 1) {
            media_song = MediaPlayer.create(this, R.raw.dove);
            media_song.start();

            this.isRunning = true;

            NotificationManager notift_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent intent_main_activity = new Intent(this.getApplicationContext(), Alarmy.class);
            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0, intent_main_activity, 0);

            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("Przypomnienie o posiłku")
                    .setContentText("Włącz alarmy")
                    .setContentIntent(pending_intent_main_activity)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setAutoCancel(false).build();

            notift_manager.notify(0, notification_popup);

        } else if (this.isRunning && startId == 0) {
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
        } else if (!this.isRunning && startId == 0) {

            this.isRunning = false;
        } else if (this.isRunning && startId == 1) {
            this.isRunning = true;
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Toast.makeText(this, "On destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        this.isRunning = false;
    }
}
