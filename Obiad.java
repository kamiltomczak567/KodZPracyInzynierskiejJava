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

public class Obiad extends AppCompatActivity {

    BazaDanych bazaDanych;
    TextView textView43;
    public float kalorie;
    String pobierz3;
    Spinner spinner6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obiad);

        bazaDanych = new BazaDanych(this);
        textView43 = findViewById(R.id.textView43);

        SharedPreferences obiad = getSharedPreferences("PREF4", MODE_PRIVATE);
        textView43.setText(obiad.getString("value4", ""));

        spinner6 = findViewById(R.id.spinner6);
        String[] lista2 = {"", "Sniadnaie", "II Sniadnaie", "Podwieczorek", "Kolacja"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista2);
        spinner6.setAdapter(adapter2);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        //wybrano nic

                        break;
                    case 1:
                        //wybrano sniadanie
                        Intent intent = new Intent(Obiad.this, Sniadanie.class);
                        startActivity(intent);

                        break;
                    case 2:
                        //wybrano II sniadanie
                        Intent intent2 = new Intent(Obiad.this, DrugieSniadanie.class);
                        startActivity(intent2);

                        break;
                    case 3:
                        //wybrano podwieczorek
                        Intent intent3 = new Intent(Obiad.this, Podwieczorek.class);
                        startActivity(intent3);

                        break;
                    case 4:
                        //wybrano kolacje
                        Intent intent4 = new Intent(Obiad.this, Kolacja.class);
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
        SharedPreferences settings3 = PreferenceManager.getDefaultSharedPreferences(this);
        int lastTimeStarted = settings3.getInt("last_time_started3", -1);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (today != lastTimeStarted) {
            //startSomethingOnce();
            wyswietObiad(null);

            SharedPreferences.Editor editor = settings3.edit();
            editor.putInt("last_time_started3", today);
            editor.commit();
        }
    }

    public void wyswietObiad(View view) {
        SharedPreferences ustawienia = getSharedPreferences("PREF", MODE_PRIVATE);
        String aaa = ustawienia.getString("value", "");
        kalorie = Float.valueOf(aaa);

        if (kalorie <= 2000) {
            pobierz3 = bazaDanych.pobierzObiad(450, 520);
            textView43.setText(pobierz3);
        }
        if (kalorie > 2000 && kalorie <= 2500) {
            pobierz3 = bazaDanych.pobierzObiad(510, 810);
            textView43.setText(pobierz3);
        }
        if (kalorie > 2500 && kalorie <= 3000) {
            pobierz3 = bazaDanych.pobierzObiad(810, 965);
            textView43.setText(pobierz3);
        }
        if (kalorie > 3000 && kalorie <= 3500) {
            pobierz3 = bazaDanych.pobierzObiad(890, 1150);
            textView43.setText(pobierz3);
        }
        if (kalorie > 3500 && kalorie <= 4000) {
            pobierz3 = bazaDanych.pobierzObiad(1140, 1420);
            textView43.setText(pobierz3);
        }
        SharedPreferences obiad = getSharedPreferences("PREF4", MODE_PRIVATE);
        SharedPreferences.Editor editor4 = obiad.edit();
        editor4.putString("value4", pobierz3);
        editor4.commit();
    }

    public void deser(View view) {
        startActivity(new Intent(Obiad.this, Desery.class));
    }

    public void ladujWtorek3(View view) {
        startActivity(new Intent(Obiad.this, DrugieSniadanie.class));
    }

    public void ladujCzwartek3(View view) {
        startActivity(new Intent(Obiad.this, Podwieczorek.class));
    }

    public void ladujPiatek3(View view) {
        startActivity(new Intent(Obiad.this, Kolacja.class));
    }

    public void ladujSobota3(View view) {
        startActivity(new Intent(Obiad.this, Sobota.class));
    }

    public void ladujNiedziela3(View view) {
        startActivity(new Intent(Obiad.this, Niedziela.class));
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Obiad.this, SpisDietPrzyciski.class));
    }
}
