package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class BMI extends AppCompatActivity {

    RadioButton radioButton11;
    RadioButton radioButton12;
    boolean wybierz1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
    }

    public void obliczBMI(View view) {
        EditText editText = findViewById(R.id.editText);
        EditText editText2 = findViewById(R.id.editText2);

        Float kg = Float.valueOf(editText.getText().toString());
        Float cm = Float.valueOf(editText2.getText().toString());

        Float licz = (kg / ((cm * cm) / 10000));
        licz = (float) Math.round(licz * 100) / 100;

        sprawdzIWypiszBMI(licz);
    }

    private void sprawdzIWypiszBMI(Float licz) {
        if (radioButton12.isChecked()) {
            if (licz < 15) {
                wypiszWynik(licz, "wyniszczenie");
            }
            if (licz >= 15 & licz < 16.5) {
                wypiszWynik(licz, "wychudzenie");
            }
            if (licz >= 16.5 & licz < 20.5) {
                wypiszWynik(licz, "niedowaga");
            }
            if (licz >= 20.5 & licz < 26.5) {
                wypiszWynik(licz, "wartość prawidłowa");
            }
            if (licz >= 26.5 & licz < 29.9) {
                wypiszWynik(licz, "nadwaga");
            }
            if (licz >= 29.9 & licz < 34.9) {
                wypiszWynik(licz, "1 stopien otyłości");
            }
            if (licz >= 34.9 & licz < 39.9) {
                wypiszWynik(licz, "2 stopien otyłości");
            }
            if (licz >= 39.9) {
                wypiszWynik(licz, "3 stopien otyłości");
            }
        }
        if (radioButton11.isChecked()) {
            if (licz < 15) {
                wypiszWynik(licz, "wyniszczenie");
            }
            if (licz >= 15 & licz < 16.5) {
                wypiszWynik(licz, "wychudzenie");
            }
            if (licz >= 16.5 & licz < 19) {
                wypiszWynik(licz, "niedowaga");
            }
            if (licz >= 19 & licz < 25.8) {
                wypiszWynik(licz, "wartość prawidłowa");
            }
            if (licz >= 25.8 & licz < 29.9) {
                wypiszWynik(licz, "nadwaga");
            }
            if (licz >= 29.9 & licz < 34.9) {
                wypiszWynik(licz, "1 stopien otyłości");
            }
            if (licz >= 34.9 & licz < 39.9) {
                wypiszWynik(licz, "2 stopien otyłości");
            }
            if (licz >= 39.9) {
                wypiszWynik(licz, "3 stopien otyłości");
            }
        }
    }

    private void wypiszWynik(Float licz, String nazwaPrawidlowosci){
        TextView textView = findViewById(R.id.textView4);

        textView.setText("Twój wynik to: " + licz.toString() + "\n" + nazwaPrawidlowosci);
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(BMI.this, Kalkulatory.class));
    }

    public void radioButton1(View view) {
        radioButton11 = findViewById(R.id.radioButton11);
        radioButton12 = findViewById(R.id.radioButton12);
        wybierz1 = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton12:
                if (wybierz1) {
                    Toast.makeText(this, "Mezczyzna", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.radioButton11:
                if (wybierz1){ //kobieta
                    Toast.makeText(this, "Kobieta", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }
}
