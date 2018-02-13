package com.example.kamil.my_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

public class DrugaAktywnosc extends AppCompatActivity {
    BazaDanych myDbHelper;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druga_aktywnosc);

        PACKAGE_NAME = getApplicationContext().getPackageName();
        myDbHelper = new BazaDanych(this);

        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            try {
                throw sqle;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean dialogShown = settings.getBoolean("dialogShown", false);

        if (!dialogShown) {
            wyswietlLosowyKomunikat();
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("dialogShown", true);
            editor.commit();
        }

        Calendar c = Calendar.getInstance();
        int currentTimeSeconds = c.get(Calendar.SECOND);
        SharedPreferences czas = getSharedPreferences("CZAS", MODE_PRIVATE);
        SharedPreferences.Editor editczas = czas.edit();
        editczas.putInt("czasvalue", currentTimeSeconds);
        editczas.commit();
    }

    public void wyswietlLosowyKomunikat() {
        String alert = MainActivity.wylosujKomunikat();

        AlertDialog.Builder mojAlert = new AlertDialog.Builder(this);
        mojAlert.setMessage(alert)
                .setPositiveButton("Dalej..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Czy wiesz, że ...?").create();
        mojAlert.show();
    }

    public void ladujCoPowinienesWiedziec(View view) {
        startActivity(new Intent(DrugaAktywnosc.this, ActivityCoPowinienesWiedziec.class));
    }

    public void ladujKalkulatory(View view) {
        startActivity(new Intent(DrugaAktywnosc.this, Kalkulatory.class));
    }

    public void ladujUlozDiete(View view) {
        startActivity(new Intent(DrugaAktywnosc.this, UlozDiete.class));
    }

    public void ladujSprawdzCoJesz(View view) {
        startActivity(new Intent(DrugaAktywnosc.this, SprawdzCoJesz.class));
    }

    public void ladujPoniedzialek(View view) {
        startActivity(new Intent(DrugaAktywnosc.this, SpisDietPrzyciski.class));
    }

    public void ladujAlarmy(View view) {
        startActivity(new Intent(DrugaAktywnosc.this, Alarmy.class));
    }

    public void obp(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Wyłączyć aplikację?");
        alertDialogBuilder
                .setMessage("Wciśnij tak, aby wyłączyć!")
                .setCancelable(false)
                .setPositiveButton("Tak",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(0);
                            }
                        })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
