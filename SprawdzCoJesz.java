package com.example.kamil.my_application;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SprawdzCoJesz extends AppCompatActivity {
    ListView listView;
    BazaDanych mojaBaza;
    SQLiteDatabase db;
    BazaDanych bazaDanych;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprawdz_co_jesz);
        mojaBaza = new BazaDanych(this);
        listView = findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listDataAdapter);
        bazaDanych = new BazaDanych(getApplicationContext());
        db = bazaDanych.getReadableDatabase();
        cursor = bazaDanych.pobierzInformacje(db);

        if (cursor.moveToFirst()) {
            do {
                String nazwa, kcal, bialko, tluszcz, weglowodany;
                nazwa = cursor.getString(0);
                kcal = cursor.getString(1);
                bialko = cursor.getString(2);
                tluszcz = cursor.getString(3);
                weglowodany = cursor.getString(4);
                DataProvider dataProvider = new DataProvider(nazwa, kcal, bialko, tluszcz, weglowodany);
                listDataAdapter.add(dataProvider);

            } while (cursor.moveToNext());
        }
    }

    public void zapiszSprawdzCoJesz(View view) {
        startActivity(new Intent(SprawdzCoJesz.this, ZapiszSprawdzCoJesz.class));

    }

    public void ladujWynikiZDiety(View view) {
        startActivity(new Intent(SprawdzCoJesz.this, ZapiszSprawdzCoJesz.class));

    }

    public void zaladujDrugaAktywnosc(View view) {
        startActivity(new Intent(SprawdzCoJesz.this, DrugaAktywnosc.class));
    }

    public void ladujDrugaAkty(View view) {
        startActivity(new Intent(SprawdzCoJesz.this, DrugaAktywnosc.class));
    }

}
