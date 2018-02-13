package com.example.kamil.my_application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.kamil.my_application.RingtonePlayintService;

/**
 * Created by K on 2016-02-27.
 */
public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("rc", "J");

        String get_your_string = intent.getExtras().getString("extra");

        Intent service_intent = new Intent(context, RingtonePlayintService.class);

        service_intent.putExtra("extra", get_your_string);

        context.startService(service_intent);
    }
}
