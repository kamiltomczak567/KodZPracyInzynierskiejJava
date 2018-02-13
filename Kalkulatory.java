package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Kalkulatory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulatory);
    }

    public void ladujBMI(View view) {
        startActivity(new Intent(Kalkulatory.this, BMI.class));
    }

    public void ladujWHR(View view) {
        startActivity(new Intent(Kalkulatory.this, WHR.class));
    }

    public void ladujBF(View view) {
        startActivity(new Intent(Kalkulatory.this, BF.class));
    }

    public void ladujLorentz(View view) {
        startActivity(new Intent(Kalkulatory.this, Lorentz.class));
    }

    public void ladujBroc(View view) {
        startActivity(new Intent(Kalkulatory.this, Broc.class));
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Kalkulatory.this, DrugaAktywnosc.class));
    }

}
