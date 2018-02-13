package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Broc extends AppCompatActivity {

    boolean wybierz3;
    RadioButton radioButton7;
    RadioButton radioButton8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broc);
    }

    public void obliczBroc(View view) {
        EditText editText13 = findViewById(R.id.editText13);
        TextView textView20 = findViewById(R.id.textView20);

        Float wzrost = Float.valueOf(editText13.getText().toString());
        double wynik = 0;

        if (radioButton7.isChecked()){ //kobieta
            wynik = (wzrost - 100) * 0.85;
        }
        if (radioButton8.isChecked()) {
            wynik = (wzrost - 100) * 0.9;
        }

        if(wynik != 0){
            textView20.setText("Idealna waga :" + String.valueOf(wynik) + "kg");
        }
    }

    public void radioButton3(View view) {
        radioButton7 = findViewById(R.id.radioButton7);
        radioButton8 = findViewById(R.id.radioButton8);
        wybierz3 = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton8:
                if (wybierz3) {
                    Toast.makeText(this, "Mezczyzna", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.radioButton7:
                if (wybierz3){ //kobieta
                    Toast.makeText(this, "Kobieta", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Broc.this, Kalkulatory.class));
    }
}
