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

public class Kolacja extends AppCompatActivity {

    BazaDanych bazaDanych;
    TextView textView45;
    public float kalorie;
    String pobierz5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolacja);
        bazaDanych = new BazaDanych(this);
        textView45 = findViewById(R.id.textView45);

        SharedPreferences kolacja = getSharedPreferences("PREF6", MODE_PRIVATE);
        textView45.setText(kolacja.getString("value6", ""));

        Spinner spinner8 = findViewById(R.id.spinner8);
        String[] lista2 = {"", "Sniadanie", "II Sniadanie", "Obiad", "Podwieczorek"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista2);
        spinner8.setAdapter(adapter2);
        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        //wybrano nic

                        break;
                    case 1:
                        //wybrano sniadanie
                        Intent intent = new Intent(Kolacja.this, Sniadanie.class);
                        startActivity(intent);

                        break;
                    case 2:
                        //wybrano II sniadanie
                        Intent intent2 = new Intent(Kolacja.this, DrugieSniadanie.class);
                        startActivity(intent2);

                        break;
                    case 3:
                        //wybrano obiad
                        Intent intent3 = new Intent(Kolacja.this, Obiad.class);
                        startActivity(intent3);

                        break;
                    case 4:
                        //wybrano podwieczorek
                        Intent intent4 = new Intent(Kolacja.this, Podwieczorek.class);
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
        SharedPreferences settings5 = PreferenceManager.getDefaultSharedPreferences(this);
        int lastTimeStarted = settings5.getInt("last_time_started5", -1);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (today != lastTimeStarted) {
            wyswietKolacje(null);

            SharedPreferences.Editor editor = settings5.edit();
            editor.putInt("last_time_started5", today);
            editor.commit();
        }
    }

    public void wyswietKolacje(View view) {
        SharedPreferences ustawienia = getSharedPreferences("PREF", MODE_PRIVATE);
        String aaa = ustawienia.getString("value", "");
        //textView.setText(aaa);
        kalorie = Float.valueOf(aaa);


        if (kalorie <= 2000) {
            pobierz5 = bazaDanych.pobierzkolacje(420, 520);
            textView45.setText(pobierz5);
        }
        if (kalorie > 2000 && kalorie <= 2500) {
            pobierz5 = bazaDanych.pobierzkolacje(500, 520);
            textView45.setText(pobierz5);
        }
        if (kalorie > 2500 && kalorie <= 3000) {
            pobierz5 = bazaDanych.pobierzkolacje(450, 520);
            textView45.setText(pobierz5);
        }
        if (kalorie > 3000 && kalorie <= 3500) {
            pobierz5 = bazaDanych.pobierzkolacje(500, 560);
            textView45.setText(pobierz5);
        }
        if (kalorie > 3500 && kalorie <= 4000) {
            pobierz5 = bazaDanych.pobierzkolacje(560, 620);
            textView45.setText(pobierz5);
        }
        SharedPreferences kolacja = getSharedPreferences("PREF6", MODE_PRIVATE);
        SharedPreferences.Editor editor6 = kolacja.edit();
        editor6.putString("value6", pobierz5);
        editor6.commit();
    }

    public void ladujPoniedzialek5(View view) {
        startActivity(new Intent(Kolacja.this, Sniadanie.class));
    }

    public void ladujWtorek5(View view) {
        startActivity(new Intent(Kolacja.this, DrugieSniadanie.class));
    }

    public void ladujSroda5(View view) {
        startActivity(new Intent(Kolacja.this, Obiad.class));
    }

    public void ladujCzwartek5(View view) {
        startActivity(new Intent(Kolacja.this, Podwieczorek.class));
    }

    public void ladujSobota5(View view) {
        startActivity(new Intent(Kolacja.this, Sobota.class));
    }

    public void ladujNiedziela5(View view) {
        startActivity(new Intent(Kolacja.this, Niedziela.class));
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Kolacja.this, SpisDietPrzyciski.class));
    }
}
