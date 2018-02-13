package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZapiszSprawdzCoJesz extends AppCompatActivity {
    BazaDanych mojaBaza;
    EditText nazwa, kcal, bialko, tluszcz, weglowodany;
    Button przyciskZapisz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapisz_sprawdz_co_jesz);
        mojaBaza = new BazaDanych(this);
        nazwa = findViewById(R.id.editText3);
        kcal = findViewById(R.id.editText4);
        bialko = findViewById(R.id.editText5);
        tluszcz = findViewById(R.id.editText6);
        weglowodany = findViewById(R.id.editText7);
        przyciskZapisz = findViewById(R.id.button9);
        DodajWartosci();
        //  zaladujSprawdzCoJesz(przyciskZapisz);
    }

    public void zaladujSprawdzCoJesz(View view) {
        startActivity(new Intent(ZapiszSprawdzCoJesz.this, SprawdzCoJesz.class));
    }

    public void DodajWartosci() {
        przyciskZapisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean jestZapisane = mojaBaza.insertData(nazwa.getText().toString(),
                                kcal.getText().toString(),
                                bialko.getText().toString(),
                                tluszcz.getText().toString(),
                                weglowodany.getText().toString());
                        if (jestZapisane) {
                            Toast.makeText(ZapiszSprawdzCoJesz.this, "Zapisano", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ZapiszSprawdzCoJesz.this, "Nie zapisano", Toast.LENGTH_LONG).show();
                        }

                        nazwa.setText("");
                        kcal.setText("");
                        bialko.setText("");
                        tluszcz.setText("");
                        weglowodany.setText("");
                    }
                }
        );
    }
}
