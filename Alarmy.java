package com.example.kamil.my_application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarmy extends AppCompatActivity {

    private static final String SHARED_PREFERENCES_PIERWSZY = "pierwszy";
    private static final String SHARED_PREFERENCES_DRUGI = "drugi";
    private static final String SHARED_PREFERENCES_TRZECI = "trzeci";
    private static final String SHARED_PREFERENCES_CZWARTY = "czwarty";
    private static final String SHARED_PREFERENCES_PIATY = "piaty";

    AlarmManager alarm_manager;
    AlarmManager alarm_manager2;
    AlarmManager alarm_manager3;
    AlarmManager alarm_manager4;
    AlarmManager alarm_manager5;

    TimePicker alarm_timepicker;

    TextView update_text;

    Context context;
    Context context2;
    Context context3;
    Context context4;
    Context context5;

    PendingIntent pending_intend;
    PendingIntent pending_intend2;
    PendingIntent pending_intend3;
    PendingIntent pending_intend4;
    PendingIntent pending_intend5;

    TextView update_text2;
    TextView update_text3;
    TextView update_text4;
    TextView update_text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmy);
        alarm_timepicker = findViewById(R.id.timePicker);
        alarm_timepicker.setIs24HourView(true);

        ustawPoczatkoweUstawieniaAlarmow();
    }

    private void ustawPoczatkoweUstawieniaAlarmow(){
        this.context = this;
        ustawAlarm((AlarmManager) getSystemService(ALARM_SERVICE), findViewById(R.id.update_text), Calendar.getInstance(), findViewById(R.id.alarm_on), findViewById(R.id.alarm_off), new Intent(this.context, Alarm_Receiver.class), SHARED_PREFERENCES_PIERWSZY, pending_intend);
        this.context2 = this;
        ustawAlarm((AlarmManager) getSystemService(ALARM_SERVICE), findViewById(R.id.update_text2), Calendar.getInstance(), findViewById(R.id.alarm_on2), findViewById(R.id.alarm_off2), new Intent(this.context2, Alarm_Receiver.class), SHARED_PREFERENCES_DRUGI, pending_intend2);
        this.context3 = this;
        ustawAlarm((AlarmManager) getSystemService(ALARM_SERVICE), findViewById(R.id.update_text3), Calendar.getInstance(), findViewById(R.id.alarm_on3), findViewById(R.id.alarm_off3), new Intent(this.context3, Alarm_Receiver.class), SHARED_PREFERENCES_TRZECI, pending_intend3);
        this.context4 = this;
        ustawAlarm((AlarmManager) getSystemService(ALARM_SERVICE), findViewById(R.id.update_text4), Calendar.getInstance(), findViewById(R.id.alarm_on4), findViewById(R.id.alarm_off4), new Intent(this.context4, Alarm_Receiver.class), SHARED_PREFERENCES_CZWARTY, pending_intend4);
        this.context5 = this;
        ustawAlarm((AlarmManager) getSystemService(ALARM_SERVICE), findViewById(R.id.update_text5), Calendar.getInstance(), findViewById(R.id.alarm_on5), findViewById(R.id.alarm_off5), new Intent(this.context5, Alarm_Receiver.class), SHARED_PREFERENCES_PIATY, pending_intend5);

        ustawPreferencje();
    }

    private void ustawAlarm(final AlarmManager alarmManager, final TextView updateText, final Calendar calendar, final Button alarmOn, final Button alarmOff, final Intent myIntent, final String sharedPreferencesName, final PendingIntent pendingIntend) {

        alarmOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

        /*        if(hour >12)
                {
                    hour_string = String.valueOf(hour -12);
                }*/
                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                setAlarmText("Przypomnienie " + hour_string + ":" + minute_string, sharedPreferencesName);

                long _alarm = 0;
                Calendar now = Calendar.getInstance();

                if (calendar.getTimeInMillis() <= now.getTimeInMillis()) {
                    _alarm = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY + 1);
                } else {
                    _alarm = calendar.getTimeInMillis();
                }

                myIntent.putExtra("extra", "alarm on");

                setPendingIntent(pendingIntend, myIntent);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, AlarmManager.INTERVAL_DAY, pending_intend);
            }
        });

        alarmOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarmText("Przypomnienie wyłączone", sharedPreferencesName);
                alarmManager.cancel(pending_intend);
                myIntent.putExtra("extra", "alarm off");
                sendBroadcast(myIntent);
            }
        });
    }

    private void setAlarmText(String output, final String sharedPreferencesName) {
        update_text.setText(output);
        SharedPreferences u = getSharedPreferences(sharedPreferencesName, MODE_PRIVATE);
        SharedPreferences.Editor editor = u.edit();
        editor.putString(sharedPreferencesName, output);
        editor.commit();
    }

    private void setPendingIntent(PendingIntent pendingIntend, Intent myIntent){
        pendingIntend = PendingIntent.getBroadcast(Alarmy.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void ustawPreferencje() {
        SharedPreferences u = getSharedPreferences(SHARED_PREFERENCES_PIERWSZY, MODE_PRIVATE);
        update_text.setText(u.getString(SHARED_PREFERENCES_PIERWSZY, "ustawiłeś alarm?"));

        SharedPreferences u2 = getSharedPreferences(SHARED_PREFERENCES_DRUGI, MODE_PRIVATE);
        update_text2.setText(u2.getString(SHARED_PREFERENCES_DRUGI, "ustawiłeś alarm?"));

        SharedPreferences u3 = getSharedPreferences(SHARED_PREFERENCES_TRZECI, MODE_PRIVATE);
        update_text3.setText(u3.getString(SHARED_PREFERENCES_TRZECI, "ustawiłeś alarm?"));

        SharedPreferences u4 = getSharedPreferences(SHARED_PREFERENCES_CZWARTY, MODE_PRIVATE);
        update_text4.setText(u4.getString(SHARED_PREFERENCES_CZWARTY, "ustawiłeś alarm?"));

        SharedPreferences u5 = getSharedPreferences(SHARED_PREFERENCES_PIATY, MODE_PRIVATE);
        update_text5.setText(u5.getString(SHARED_PREFERENCES_PIATY, "ustawiłeś alarm?"));
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Alarmy.this, DrugaAktywnosc.class));
    }
}
