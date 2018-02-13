package com.example.kamil.my_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SpisDietPrzyciski extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spis_diet_przyciski);
        TextView textView53 = findViewById(R.id.textView53);
        SharedPreferences ustawienia = getSharedPreferences("PREF", MODE_PRIVATE);
        textView53.setText("Dieta na " + ustawienia.getString("value", "0") + " kcal");
    }

    public void ladujPon(View view) {
        startActivity(new Intent(SpisDietPrzyciski.this, Sniadanie.class));
    }

    public void ladujWt(View view) {
        startActivity(new Intent(SpisDietPrzyciski.this, DrugieSniadanie.class));
    }

    public void ladujSr(View view) {
        startActivity(new Intent(SpisDietPrzyciski.this, Obiad.class));
    }

    public void ladujCzw(View view) {
        startActivity(new Intent(SpisDietPrzyciski.this, Podwieczorek.class));
    }

    public void ladujPi(View view) {
        startActivity(new Intent(SpisDietPrzyciski.this, Kolacja.class));
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(SpisDietPrzyciski.this, DrugaAktywnosc.class));
    }

    public void ladujUlozDiete(View view) {
        startActivity(new Intent(SpisDietPrzyciski.this, UlozDiete.class));
    }
}
