package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BF extends AppCompatActivity {

    boolean wybierz1;
    RadioButton radioButton3;
    RadioButton radioButton4;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bf);
    }

    public void obliczBF(View view) {
        EditText editText10 = findViewById(R.id.editText10);
        EditText editText11 = findViewById(R.id.editText11);

        Float obwTalii = Float.valueOf(editText10.getText().toString());
        Float masaCiala = Float.valueOf(editText11.getText().toString());
        double a = obwTalii * 4.15;
        double b = (a / 2.54);
        double c = 0.082 * masaCiala * 2.2;
        double d = 0, e;
        double wynik;

        if (radioButton3.isChecked()) {
            d = b - c - 76.76;
        }
        if (radioButton4.isChecked()) {
            d = b - c - 98.42;
        }

        if(d != 0){
            e = masaCiala * 2.2;
            wynik = ((d / e) * 100);
            wynik = (float) Math.round(wynik * 100) / 100;

            wypiszBF(wynik);
        }
    }

    private void wypiszBF(Double wartosc){
        TextView textView = findViewById(R.id.textView15);
        textView.setText("Procentowa zawartość tkanki tłuszczowej w organizmie " + "\n" + String.valueOf(wartosc) + " %");
    }

    public void radioButton1(View view) {
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        wybierz1 = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton4:
                if (wybierz1) {
                    Toast.makeText(this, "Mezczyzna", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.radioButton3:
                if (wybierz1){ //kobieta
                    Toast.makeText(this, "Kobieta", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(BF.this, Kalkulatory.class));
    }

}
