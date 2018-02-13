package com.example.kamil.my_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UlozDiete extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    RadioButton radioButton9;
    RadioButton radioButton10;
    boolean wybierz4;
    Double wynikKalorii;
    String wypiszKalorie;
    Sniadanie sniadanie;

    public void ladujWynikiZDiety(View view) {
        startActivity(new Intent(UlozDiete.this, ZapiszSprawdzCoJesz.class));
    }

    public void oblicz(View view) {
        ustawWartosciPoczatkowe();

        pom = (tea + tea2) / 7;
        suma = bmr + pom;
        suma2 = suma + neat;
        tdee = suma2 + (0.1 * suma2);
        if (radioButton10.isChecked()) {
            wynikKalorii = (tdee + 5);
        }
        if (radioButton9.isChecked()) {
            wynikKalorii = (tdee - 161);
        }

        wynikKalorii = (double) Math.round(wynikKalorii * 100) / 100;
        wypiszKalorie = String.valueOf(wynikKalorii);
        tv10.setText("Wynik " + (wypiszKalorie) + " kcal");

        SharedPreferences ustawienia = getSharedPreferences("PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = ustawienia.edit();
        editor.putString("value", wypiszKalorie);
        editor.commit();
        Intent intent = new Intent(UlozDiete.this, SpisDietPrzyciski.class);
        intent.putExtra("wypiszKalorie", wypiszKalorie);
        startActivity(intent);
    }

    private void ustawWartosciPoczatkowe() {
        tv10 = findViewById(R.id.texview111);

        ustawBmr();
        ustawTea();
        ustawTea2();
        ustawNeat();
    }

    private void ustawBmr(){
        if (redukcja == 200) {
            bmr = ((9.99 * waga) + (6.25 * wzrost)) - (4.92 * wiek) - redukcja;
        }
        if (idealna == 0) {
            bmr = ((9.99 * waga) + (6.25 * wzrost)) - (4.92 * wiek);
        }
        if (masa == 200) {
            bmr = ((9.99 * waga) + (6.25 * wzrost)) - (4.92 * wiek) + masa;
        }
    }

    private void ustawTea() {
        if (intensywnosc == 1) {
            tea = (liczba_treningow_w_tygodniu * sredni_czas_trwania_treningu * 7);
        }
        if (intensywnosc == 2) {
            tea = (liczba_treningow_w_tygodniu * sredni_czas_trwania_treningu * 8);
        }
        if (intensywnosc == 3) {
            tea = (liczba_treningow_w_tygodniu * sredni_czas_trwania_treningu * 9);
        }

        tea = tea + tea * 0.06;
    }

    private void ustawTea2(){
        if (intensywnosc1 == 1) {
            tea2 = (liczba_treningow_w_tygodniu1 * sredni_czas_trwania_treningu1 * 5) + 5;
        }
        if (intensywnosc1 == 2) {
            tea2 = (liczba_treningow_w_tygodniu1 * sredni_czas_trwania_treningu1 * 7) + 35;
        }
        if (intensywnosc1 == 3) {
            tea2 = (liczba_treningow_w_tygodniu1 * sredni_czas_trwania_treningu1 * 10) + 180;
        }
    }

    private void ustawNeat(){
        if (typ_budowy == 1 & pozycja == 1) {
            neat = 200;
        }
        if (typ_budowy == 1 & pozycja == 2) {
            neat = 300;
        }
        if (typ_budowy == 1 & pozycja == 3) {
            neat = 400;
        }
        if (typ_budowy == 3 & pozycja == 1) {
            neat = 700;
        }
        if (typ_budowy == 3 & pozycja == 2) {
            neat = 800;
        }
        if (typ_budowy == 3 & pozycja == 3) {
            neat = 900;
        }
        if (typ_budowy == 2 & pozycja == 1) {
            neat = 400;
        }
        if (typ_budowy == 2 & pozycja == 2) {
            neat = 450;
        }
        if (typ_budowy == 2 & pozycja == 3) {
            neat = 500;
        }
    }

    public SeekBar sb1, sb2, sb3, sb4, sb5, sb6, sb7, sb8, sb9;
    public int wiek, wzrost, waga, liczba_treningow_w_tygodniu, sredni_czas_trwania_treningu, intensywnosc;
    public int liczba_treningow_w_tygodniu1, sredni_czas_trwania_treningu1, intensywnosc1;
    public TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
    public int typ_budowy, pozycja, redukcja, idealna, masa;
    public double tdee, bmr, tea, tea2, tef, neat, pom, suma, suma2;
    public Spinner spinner2, spinner, spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uloz_diete);

        ustawSb();
        ustawTv();

        spinner3 = findViewById(R.id.spinner3);
        String[] lista2 = {"       WYBIERZ RODZAJ DIETY", "       Dieta redukcyjna", "       Idealna waga", "       Dieta na przyrost masy"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista2);
        spinner3.setAdapter(adapter2);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        break;
                    case 1:
                        redukcja = 200;
                        Toast.makeText(UlozDiete.this, "Wybrano opcję redukcja ", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        idealna = 0;
                        Toast.makeText(UlozDiete.this, "Wybrano opcję idealna waga ", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        masa = 200;
                        Toast.makeText(UlozDiete.this, "Wybrano opcję dieta na masę ", Toast.LENGTH_SHORT).show();
                        break;
                }
                int userChoice3 = spinner3.getSelectedItemPosition();
                SharedPreferences sharedPref3 = getSharedPreferences("FileName3", 0);
                SharedPreferences.Editor prefEditor3 = sharedPref3.edit();
                prefEditor3.putInt("userChoiceSpinner3", userChoice3);
                prefEditor3.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2 = findViewById(R.id.spinner2);
        String[] lista3 = {"       TRYB PRACY", "       Niski", "       Sredni", "       Wysoki"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista3);
        spinner2.setAdapter(adapter3);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        break;
                    case 1:
                        pozycja = 1;
                        Toast.makeText(UlozDiete.this, "Tryb pracy niski", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        pozycja = 2;
                        Toast.makeText(UlozDiete.this, "Tryb pracy sredni", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        pozycja = 3;
                        Toast.makeText(UlozDiete.this, "Tryb pracy wysoki", Toast.LENGTH_SHORT).show();
                        break;
                }
                int userChoice2 = spinner2.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getSharedPreferences("FileName2", 0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("userChoiceSpinner2", userChoice2);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //SPINER
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] lista = {"       TYP BUDOWY CIALA", "       Endomorfik", "       Mezomorfik", "       Ektomorfik"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id) {
                    case 0:
                        break;
                    case 1:
                        Toast.makeText(UlozDiete.this, "Endomorfik", Toast.LENGTH_SHORT).show();
                        typ_budowy = 1;

                        break;
                    case 2:
                        Toast.makeText(UlozDiete.this, "Mezomorfik", Toast.LENGTH_SHORT).show();
                        typ_budowy = 2;

                        break;
                    case 3:
                        Toast.makeText(UlozDiete.this, "Ektomorfik", Toast.LENGTH_SHORT).show();
                        typ_budowy = 3;

                        break;
                }
                int userChoice = spinner.getSelectedItemPosition();
                SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("userChoiceSpinner", userChoice);
                prefEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void ustawSb() {
        sb1 = findViewById(R.id.seekBar);
        sb3 = findViewById(R.id.seekBar3);
        sb2 = findViewById(R.id.seekBar2);
        sb4 = findViewById(R.id.seekBar4);
        sb5 = findViewById(R.id.seekBar5);
        sb6 = findViewById(R.id.seekBar6);
        sb7 = findViewById(R.id.seekBar10);
        sb8 = findViewById(R.id.seekBar11);
        sb9 = findViewById(R.id.seekBar12);

        sb1.setOnSeekBarChangeListener(this);
        sb2.setOnSeekBarChangeListener(this);
        sb3.setOnSeekBarChangeListener(this);
        sb4.setOnSeekBarChangeListener(this);
        sb5.setOnSeekBarChangeListener(this);
        sb6.setOnSeekBarChangeListener(this);
        sb7.setOnSeekBarChangeListener(this);
        sb8.setOnSeekBarChangeListener(this);
        sb9.setOnSeekBarChangeListener(this);
    }

    private void ustawTv() {
        tv1 = findViewById(R.id.textView5);
        tv2 = findViewById(R.id.textView6);
        tv3 = findViewById(R.id.textView7);
        tv4 = findViewById(R.id.textView8);
        tv5 = findViewById(R.id.textView9);
        tv6 = findViewById(R.id.textView10);
        tv7 = findViewById(R.id.textView16);
        tv8 = findViewById(R.id.textView17);
        tv9 = findViewById(R.id.textView18);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBar:
                tv1.setText("Wiek: " + progress);
                wiek = progress;

                break;
            case R.id.seekBar2:
                tv2.setText("Wzrost: " + progress);
                wzrost = progress;

                break;
            case R.id.seekBar3:
                tv3.setText("Waga[kg]: " + progress);
                waga = progress;
                break;
            case R.id.seekBar4:
                tv4.setText("Liczba treningow w tygodniu: " + progress);
                liczba_treningow_w_tygodniu = progress;
                break;
            case R.id.seekBar5:
                tv5.setText("Sredni czas trwania treningu[min] " + progress);
                sredni_czas_trwania_treningu = progress;
                break;
            case R.id.seekBar6:
                tv6.setText("Intensywnosc treningu w skali [od 1 do 3] " + progress);
                intensywnosc = progress;
                break;
            case R.id.seekBar10:
                tv7.setText("Liczba treningow w tygodniu: " + progress);
                liczba_treningow_w_tygodniu1 = progress;
                break;
            case R.id.seekBar11:
                tv8.setText("Sredni czas trwania treningu[min] " + progress);
                sredni_czas_trwania_treningu1 = progress;
                break;
            case R.id.seekBar12:
                tv9.setText("Intensywnosc treningu w skali [od 1 do 3] " + progress);
                intensywnosc1 = progress;
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        edytujPreferencje("Seekbar1", "wiek", wiek);
        edytujPreferencje("Seekbar2", "wzrost", wzrost);
        edytujPreferencje("Seekbar3", "waga", waga);
        edytujPreferencje("Seekbar4", "liczbatreningow", liczba_treningow_w_tygodniu);
        edytujPreferencje("Seekbar5", "sredniczastrwania", sredni_czas_trwania_treningu);
        edytujPreferencje("Seekbar6", "intensywnosc", intensywnosc);
        edytujPreferencje("Seekbar7", "liczbatreningow1", liczba_treningow_w_tygodniu1);
        edytujPreferencje("Seekbar8", "sredniczastrwania1", sredni_czas_trwania_treningu1);
        edytujPreferencje("Seekbar9", "intensywnosc1", intensywnosc1);
    }

    private void edytujPreferencje(String numerSeekbara, String nazwaDoEdycji, Integer wartoscDoEdycji){
        SharedPreferences wiekseekbar = getSharedPreferences(numerSeekbara, MODE_PRIVATE);
        SharedPreferences.Editor editor = wiekseekbar.edit();
        editor.putInt(nazwaDoEdycji, wartoscDoEdycji);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ustawSeekbar(sb1, "Seekbar1", "wiek");
        ustawSeekbar(sb2, "Seekbar2", "wzrost");
        ustawSeekbar(sb3, "Seekbar3", "waga");
        ustawSeekbar(sb4, "Seekbar4", "liczbatreningow");
        ustawSeekbar(sb5, "Seekbar5", "sredniczastrwania");
        ustawSeekbar(sb6, "Seekbar6", "intensywnosc");
        ustawSeekbar(sb7, "Seekbar7", "liczbatreningow1");
        ustawSeekbar(sb8, "Seekbar8", "sredniczastrwania1");
        ustawSeekbar(sb9, "Seekbar9", "intensywnosc1");

        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue != -1) {
            spinner.setSelection(spinnerValue);
        }

        SharedPreferences sharedPref3 = getSharedPreferences("FileName3", MODE_PRIVATE);
        int spinnerValue3 = sharedPref3.getInt("userChoiceSpinner3", -1);
        if (spinnerValue3 != -1) {
            spinner3.setSelection(spinnerValue3);
        }

        SharedPreferences sharedPref2 = getSharedPreferences("FileName2", MODE_PRIVATE);
        int spinnerValue2 = sharedPref2.getInt("userChoiceSpinner2", -1);
        if (spinnerValue2 != -1) {
            spinner2.setSelection(spinnerValue2);
        }
    }

    private void ustawSeekbar(SeekBar seekBar, String numerSeekbara, String nazwaPreferencji){
        SharedPreferences wiekseekbar = getSharedPreferences("numerSeekbara", MODE_PRIVATE);
        Integer aaa = wiekseekbar.getInt("nazwaPreferencji", 0);
        seekBar.setProgress(aaa);
    }

    public void radioButton4(View view) {
        radioButton9 = findViewById(R.id.radioButton9);
        radioButton10 = findViewById(R.id.radioButton10);

        wybierz4 = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton10:
                if (wybierz4) {
                    Toast.makeText(this, "Mezczyzna", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.radioButton9:
                if (wybierz4) { //kobieta
                    Toast.makeText(this, "Kobieta", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Toast.makeText(UlozDiete.this,wzrost , Toast.LENGTH_SHORT).show();
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(UlozDiete.this, DrugaAktywnosc.class));
    }
}



