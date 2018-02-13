package com.example.kamil.my_application;

import android.content.Context;
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
import android.widget.Toast;

import java.util.Calendar;

public class DrugieSniadanie extends AppCompatActivity {

    BazaDanych bazaDanych;
    TextView textView42;
    public float kalorie;
    String pobierz2;
    Spinner spinner5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugiesniadanie);
        bazaDanych = new BazaDanych(this);
        textView42 = findViewById(R.id.textView42);

        SharedPreferences sniadanieII = getSharedPreferences("PREF3", MODE_PRIVATE);
        textView42.setText(sniadanieII.getString("value3", ""));

        spinner5 = findViewById(R.id.spinner5);
        String[] lista2 = {"", "Sniadanie", "Obiad", "Podwieczorek", "Kolacja"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista2);
        spinner5.setAdapter(adapter2);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        //wybrano nic
                        break;
                    case 1:
                        //wybrano sniadanie
                        Intent intent = new Intent(DrugieSniadanie.this, Sniadanie.class);
                        startActivity(intent);

                        break;
                    case 2:
                        //wybrano obiad
                        Intent intent2 = new Intent(DrugieSniadanie.this, Obiad.class);
                        startActivity(intent2);

                        break;
                    case 3:
                        //wybrano podwieczorek
                        Intent intent3 = new Intent(DrugieSniadanie.this, Podwieczorek.class);
                        startActivity(intent3);

                        break;
                    case 4:
                        //wybrano kolacje
                        Intent intent4 = new Intent(DrugieSniadanie.this, Kolacja.class);
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
        SharedPreferences settings2 = PreferenceManager.getDefaultSharedPreferences(this);
        int lastTimeStarted = settings2.getInt("last_time_started2", -1);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (today != lastTimeStarted) {
            //startSomethingOnce();
            wyswietlIISniadania(null);

            SharedPreferences.Editor editor = settings2.edit();
            editor.putInt("last_time_started2", today);
            editor.commit();
        }
    }

    public void wyswietlIISniadania(View view) {
        SharedPreferences ustawienia = getSharedPreferences("PREF", MODE_PRIVATE);
        String aaa = ustawienia.getString("value", "");
        kalorie = Float.valueOf(aaa);

        if (kalorie <= 2000) {
            pobierz2 = bazaDanych.pobierzIISniadania(220, 250);
            textView42.setText(pobierz2);
        }
        if (kalorie > 2000 && kalorie <= 2500) {
            pobierz2 = bazaDanych.pobierzIISniadania(240, 410);
            textView42.setText(pobierz2);
        }
        if (kalorie > 2500 && kalorie <= 3000) {
            pobierz2 = bazaDanych.pobierzIISniadania(390, 440);
            textView42.setText(pobierz2);
        }
        if (kalorie > 3000 && kalorie <= 3500) {
            pobierz2 = bazaDanych.pobierzIISniadania(400, 555);
            textView42.setText(pobierz2);
        }
        if (kalorie > 3500 && kalorie <= 4000) {
            pobierz2 = bazaDanych.pobierzIISniadania(500, 610);
            textView42.setText(pobierz2);
        }

        SharedPreferences sniadanieII = getSharedPreferences("PREF3", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sniadanieII.edit();
        editor3.putString("value3", pobierz2);
        editor3.commit();
    }

    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public void ladujPoniedzialek2(View view) {
        startActivity(new Intent(DrugieSniadanie.this, Sniadanie.class));
    }

    public void ladujSroda2(View view) {
        startActivity(new Intent(DrugieSniadanie.this, Obiad.class));
    }

    public void ladujCzwartek2(View view) {
        startActivity(new Intent(DrugieSniadanie.this, Podwieczorek.class));
    }

    public void ladujPiatek2(View view) {
        startActivity(new Intent(DrugieSniadanie.this, Kolacja.class));
    }

    public void ladujSobota2(View view) {
        startActivity(new Intent(DrugieSniadanie.this, Sobota.class));
    }

    public void ladujNiedziela2(View view) {
        startActivity(new Intent(DrugieSniadanie.this, Niedziela.class));
    }

    public void ladujDrugaAktywnosc2(View view) {
        startActivity(new Intent(DrugieSniadanie.this, SpisDietPrzyciski.class));
    }
}

