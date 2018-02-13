package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Niedziela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niedziela);
    }

    public void ladujPoniedzialek7(View view) {
        startActivity(new Intent(Niedziela.this, Sniadanie.class));
    }

    public void ladujWtorek7(View view) {
        startActivity(new Intent(Niedziela.this, DrugieSniadanie.class));
    }

    public void ladujSroda7(View view) {
        startActivity(new Intent(Niedziela.this, Obiad.class));
    }

    public void ladujCzwartek7(View view) {
        startActivity(new Intent(Niedziela.this, Podwieczorek.class));
    }

    public void ladujPiatek7(View view) {
        startActivity(new Intent(Niedziela.this, Kolacja.class));
    }

    public void ladujSobota7(View view) {
        startActivity(new Intent(Niedziela.this, Sobota.class));
    }

    public void ladujDrugaAktywnosc7(View view) {
        startActivity(new Intent(Niedziela.this, DrugaAktywnosc.class));
    }
}
