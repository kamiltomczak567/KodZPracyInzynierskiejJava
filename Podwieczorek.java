package com.example.kamil.my_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Podwieczorek extends AppCompatActivity {

    BazaDanych bazaDanych;
    TextView textView44;
    public float kalorie;
    String pobierz4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podwieczorek);
        bazaDanych = new BazaDanych(this);
        textView44 = findViewById(R.id.textView44);

        SharedPreferences podwieczorek = getSharedPreferences("PREF5", MODE_PRIVATE);
        textView44.setText(podwieczorek.getString("value5", ""));

        Spinner spinner7 = findViewById(R.id.spinner7);
        String[] lista2 = {"", "Sniadanie", "II Sniadanie", "Obiad", "Kolacja"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista2);
        spinner7.setAdapter(adapter2);
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        //wybrano nic

                        break;
                    case 1:
                        //wybrano sniadanie
                        Intent intent = new Intent(Podwieczorek.this, Sniadanie.class);
                        startActivity(intent);

                        break;
                    case 2:
                        //wybrano II sniadanie
                        Intent intent2 = new Intent(Podwieczorek.this, DrugieSniadanie.class);
                        startActivity(intent2);

                        break;
                    case 3:
                        //wybrano obiad
                        Intent intent3 = new Intent(Podwieczorek.this, Obiad.class);
                        startActivity(intent3);

                        break;
                    case 4:
                        //wybrano kolacje
                        Intent intent4 = new Intent(Podwieczorek.this, Kolacja.class);
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
        SharedPreferences settings4 = PreferenceManager.getDefaultSharedPreferences(this);
        int lastTimeStarted = settings4.getInt("last_time_started4", -1);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (today != lastTimeStarted) {
            //startSomethingOnce();
            wyswietPodwieczorek(null);

            SharedPreferences.Editor editor = settings4.edit();
            editor.putInt("last_time_started4", today);
            editor.commit();
        }
    }

    public void wyswietPodwieczorek(View view) {
        SharedPreferences ustawienia = getSharedPreferences("PREF", MODE_PRIVATE);
        String aaa = ustawienia.getString("value", "");
        kalorie = Float.valueOf(aaa);


        if (kalorie <= 2000) {
            pobierz4 = bazaDanych.pobierzPodwieczorek(400, 520);
            textView44.setText(pobierz4);
        }
        if (kalorie > 2000 && kalorie <= 2500) {
            pobierz4 = bazaDanych.pobierzPodwieczorek(460,600);
            textView44.setText(pobierz4);
        }
        if (kalorie > 2500 && kalorie <= 3000) {
            pobierz4 = bazaDanych.pobierzPodwieczorek(550, 680);
            textView44.setText(pobierz4);
        }
        if (kalorie > 3000 && kalorie <= 3500) {
            pobierz4 = bazaDanych.pobierzPodwieczorek(650, 720);
            textView44.setText(pobierz4);
        }
        if (kalorie > 3500 && kalorie <= 4000) {
            pobierz4 = bazaDanych.pobierzPodwieczorek(670, 730);
            textView44.setText(pobierz4);
        }
        SharedPreferences podwieczorek = getSharedPreferences("PREF5", MODE_PRIVATE);
        SharedPreferences.Editor editor5 = podwieczorek.edit();
        editor5.putString("value5", pobierz4);
        editor5.commit();
    }

    public void ladujPoniedzialek4(View view) {
        startActivity(new Intent(Podwieczorek.this, Sniadanie.class));
    }

    public void ladujWtorek4(View view) {
        startActivity(new Intent(Podwieczorek.this, DrugieSniadanie.class));
    }

    public void ladujSroda4(View view) {
        startActivity(new Intent(Podwieczorek.this, Obiad.class));
    }

    public void ladujPiatek4(View view) {
        startActivity(new Intent(Podwieczorek.this, Kolacja.class));
    }

    public void ladujSobota4(View view) {
        startActivity(new Intent(Podwieczorek.this, Sobota.class));
    }

    public void ladujNiedziela4(View view) {
        startActivity(new Intent(Podwieczorek.this, Niedziela.class));
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Podwieczorek.this, SpisDietPrzyciski.class));
    }
}
