package com.example.kamil.my_application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sniadanie extends AppCompatActivity {
    ViewPager viewPager1;
    AdapterDoPrzesuwania adapter;
    private int licz;
    public float kalorie;
    public TextView textView28, textView41;
    String wynikKalorii1;
    BazaDanych bazaDanych;
    String pobierz;
    Spinner spinner4;

    String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sniadanie);
        bazaDanych = new BazaDanych(this);
        textView41 = findViewById(R.id.textView41);

        Intent intent = getIntent();
        wynikKalorii1 = intent.getStringExtra("wypiszKalorie");

        SharedPreferences sniadanie = getSharedPreferences("PREF2", MODE_PRIVATE);
        textView41.setText(sniadanie.getString("value2", ""));

        spinner4 = findViewById(R.id.spinner4);
        String[] lista2 = {"", "II Sniadanie", "Obiad", "Podwieczorek", "Kolacja"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista2);
        spinner4.setAdapter(adapter2);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        //wybrano nic

                        break;
                    case 1:
                        //wybrano II sniadanie
                        Intent intent = new Intent(Sniadanie.this, DrugieSniadanie.class);
                        startActivity(intent);

                        break;
                    case 2:
                        //wybrano obiad
                        Intent intent2 = new Intent(Sniadanie.this, Obiad.class);
                        startActivity(intent2);

                        break;
                    case 3:
                        //wybrano podwieczorek
                        Intent intent3 = new Intent(Sniadanie.this, Podwieczorek.class);
                        startActivity(intent3);

                        break;
                    case 4:
                        //wybrano kolacje
                        Intent intent4 = new Intent(Sniadanie.this, Kolacja.class);
                        startActivity(intent4);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences settings1 = PreferenceManager.getDefaultSharedPreferences(this);
        int lastTimeStarted = settings1.getInt("last_time_started1", -1);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (today != lastTimeStarted) {
            //startSomethingOnce();
            wyswietlSniadania(null);

            SharedPreferences.Editor editor = settings1.edit();
            editor.putInt("last_time_started1", today);
            editor.commit();
        }
    }

    public void wyswietlSniadania(View view) {
        SharedPreferences ustawienia = getSharedPreferences("PREF", MODE_PRIVATE);
        String aaa = ustawienia.getString("value", "");
        kalorie = Float.valueOf(aaa);

        if (kalorie <= 2000) {
            pobierz = bazaDanych.pobierzSniadania(270, 300);
            textView41.setText(pobierz);
        }
        if (kalorie > 2000 && kalorie <= 2500) {
            pobierz = bazaDanych.pobierzSniadania(290, 480);
            textView41.setText(pobierz);
        }
        if (kalorie > 2500 && kalorie <= 3000) {
            pobierz = bazaDanych.pobierzSniadania(450, 500);
            textView41.setText(pobierz);
        }
        if (kalorie > 3000 && kalorie <= 3500) {
            pobierz = bazaDanych.pobierzSniadania(500, 670);
            textView41.setText(pobierz);
        }
        if (kalorie > 3500 && kalorie <= 4000) {
            pobierz = bazaDanych.pobierzSniadania(670, 740);
            textView41.setText(pobierz);
        }

        SharedPreferences sniadanie = getSharedPreferences("PREF2", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sniadanie.edit();
        editor2.putString("value2", pobierz);
        editor2.commit();
    }

    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void ladujWtorek1(View view) {
        startActivity(new Intent(Sniadanie.this, DrugieSniadanie.class));
    }

    public void ladujSroda1(View view) {
        startActivity(new Intent(Sniadanie.this, Obiad.class));
    }

    public void ladujCzwartek1(View view) {
        startActivity(new Intent(Sniadanie.this, Podwieczorek.class));
    }

    public void ladujPiatek1(View view) {
        startActivity(new Intent(Sniadanie.this, Kolacja.class));
    }

    public void ladujSobota1(View view) {
        startActivity(new Intent(Sniadanie.this, Sobota.class));
    }

    public void ladujNiedziela1(View view) {
        startActivity(new Intent(Sniadanie.this, Niedziela.class));
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Sniadanie.this, SpisDietPrzyciski.class));
    }
}
