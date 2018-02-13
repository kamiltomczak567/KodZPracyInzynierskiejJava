package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class WHR extends AppCompatActivity {

    boolean wybierz;
    RadioButton radioButton;
    RadioButton radioButton2;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whr);
        loadText();
    }

    public void obliczWHR(View view) {
        EditText editText8 = findViewById(R.id.editText8);
        EditText editText9 = findViewById(R.id.editText9);

        Float obwTalii = Float.valueOf(editText8.getText().toString());
        Float obwBioder = Float.valueOf(editText9.getText().toString());
        Float whr = (obwTalii / obwBioder);
        whr = (float) Math.round(whr * 100) / 100;

        //mezczyzna
        if (whr >= 1 & radioButton.isChecked()) {
            wypiszWhr(whr, "androidalny typ jabłko");
        }
        if (whr < 1 & radioButton.isChecked()) {
            wypiszWhr(whr, "ginoidalny typ gruszka");
        }

        //kobieta
        if (whr >= 0.8 & radioButton2.isChecked()) {
            wypiszWhr(whr, "androidalny typ jabłko");
        }
        if (whr < 0.8 & radioButton2.isChecked()) {
            wypiszWhr(whr, "ginoidalny typ gruszka");
        }
    }

    private void wypiszWhr(Float whr, String nazwaTypu){
        TextView textView13 = findViewById(R.id.textView13);

        textView13.setText("Twój wynik to: " + whr.toString() + "\n" + nazwaTypu);
    }

    public void radioButton(View view) {
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        wybierz = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton:
                if (wybierz) {
                    Toast.makeText(this, "Mezczyzna", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.radioButton2:
                if (wybierz) { //kobieta
                    Toast.makeText(this, "Kobieta", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    private void loadText() {
        tv1 = findViewById(R.id.textView14);
        String opis = "Ginoidalny typ gruszka - otyłość pośladkowo-udowa" + "\n" + "\n" + "Androidalny typ jabłko - otyłość centralna, spore nagromadzenie tłuszczu w okolicach jamy brzusznej";

        // tv1.setMovementMethod(new ScrollingMovementMethod());
        tv1.setText(opis);
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(WHR.this, Kalkulatory.class));
    }
}

