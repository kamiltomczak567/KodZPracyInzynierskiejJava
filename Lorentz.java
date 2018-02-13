package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Lorentz extends AppCompatActivity {

    boolean wybierz2;
    RadioButton radioButton5;
    RadioButton radioButton6;
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorentz);
    }

    public void obliczLorenz(View view) {
        EditText editText12 = findViewById(R.id.editText12);
        TextView textView19 = findViewById(R.id.textView19);

        Float wzrost = Float.valueOf(editText12.getText().toString());
        double wynik = 0;

        if (radioButton5.isChecked()) { //kobieta
            wynik = wzrost - 100 - (wzrost - 150) / 2;
        }
        if (radioButton6.isChecked()) {
            wynik = wzrost - 100 - (wzrost - 150) / 4;
        }

        if(wynik != 0){
            textView19.setText("Idealna waga : " + String.valueOf(wynik) + " kg");
        }
    }

    public void radioButton2(View view) {
        radioButton5 = findViewById(R.id.radioButton5);
        radioButton6 = findViewById(R.id.radioButton6);
        wybierz2 = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton6:
                if (wybierz2) {
                    Toast.makeText(this, "Mezczyzna", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.radioButton5:
                if (wybierz2) { //kobieta
                    Toast.makeText(this, "Kobieta", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(Lorentz.this, Kalkulatory.class));
    }

}
