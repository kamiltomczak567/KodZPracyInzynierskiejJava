package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Sobota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobota);
    }

    public void ladujPoniedzialek6(View view) {
        startActivity(new Intent(Sobota.this, Sniadanie.class));
    }

    public void ladujWtorek6(View view) {
        startActivity(new Intent(Sobota.this, DrugieSniadanie.class));
    }

    public void ladujSroda6(View view) {
        startActivity(new Intent(Sobota.this, Obiad.class));
    }

    public void ladujCzwartek6(View view) {
        startActivity(new Intent(Sobota.this, Podwieczorek.class));
    }

    public void ladujPiatek6(View view) {
        startActivity(new Intent(Sobota.this, Kolacja.class));
    }

    public void ladujNiedziela6(View view) {
        startActivity(new Intent(Sobota.this, Niedziela.class));
    }

    public void ladujDrugaAktywnosc6(View view) {
        startActivity(new Intent(Sobota.this, DrugaAktywnosc.class));
    }
}
