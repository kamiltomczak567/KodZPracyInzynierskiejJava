package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class ActivityCoPowinienesWiedziec extends AppCompatActivity {

    TextView typyBudowy;
    TextView kalkulatory, przypomnienia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_co_powinienes_wiedziec);

        // TextView  tv = (TextView) findViewById(R.id.text_view);
        typyBudowy = findViewById(R.id.textView31);
        kalkulatory = findViewById(R.id.textView33);
        przypomnienia = findViewById(R.id.tekstprzypomnien);
        loadText();
    }

    private void loadText() {
        final String przypomnieniaText = "Ektomorficy - to ludzie o drobnej budowie i małym obwodzie kości. " +
                "Charakteryzują ich zwykle długie i chude kończyny oraz wąskie ramiona." +
                " Naturalnie smukła sylwetka wynika z przyspieszonego metabolizmu. " +
                "Ektomorficy mają zwykle wyższą temperaturę ciała niż przeciętny człowiek." + "\n" +
                " " + "\n" +
                "Endomorficy – przeciętny endomorfik ma masywną budowę ciała i grube kości. " +
                "Charakteryzuje ich tendencja do tycia spowodowana wolnym metabolizmem. " +
                "Tłuszcz zbiera się u nich najczęściej wokół brzucha, co może stanowić" +
                "poważne zagrożenie dla zdrowia, powodując rozwój cukrzycy, zespołu metabolicznego i chorób serca." +
                " " + "\n" + "\n" +
                "Mezomorficy - to ludzie o muskularnej budowie,wyprostowanej postawie,a także wąskiej talii" + "\n" +
                "i szerszych barkach. Osoby te mają łatwość w budowaniu mięśni, łatwym odchudzaniu." +
                " " + "\n";
        final String kalkulatoryText = "BMI - wskaźnik masy ciała, ma znaczenie w ocenie zagrożenia chorobami związanymi z nadwagą i otyłością," +
                " np. cukrzycą, chorobą niedokrwienną serca, miażdżycą." + "\n" + "\n" +
                "WHR - stanowi wskaźnik dystrybucji tkanki tłuszczowej w ciele człowieka i jest wyznacznikiem określającym rodzaj sylwetki ciała " +
                "(sylwetka w kształcie gruszki bądź jabłka) oraz typ otyłości (otyłość brzuszna bądź pośladkowo-udowa)." + "\n" + "\n" +
                "BF - Jest to wskaźnik określajacy procentowy udział tłuszczu w masie całego ciała. " + "\n" +
                "";
        final String typyBudowyText = "Nasze dni wyglądają inaczej i pora pobudki się zmienia." +
                "Nie trzeba specjalnie budzić się szybciej, aby zjadać śniadanie i kłaść się " +
                "później spać." + "\n" + "\n" + "Ogólne założenia mówią, że pierwsze śniadanie powinniśmy zjadać nie później " +
                "niż godzinę po przebudzeniu. Odstępy między posiłkami powinny wynosić około 2,5-3,5h." +
                "Kolejne posiłki staramy się zjadać w podobnych odstępach czasowych." + "\n";

        przypomnienia.setText(typyBudowyText);
        typyBudowy.setText(przypomnieniaText);
        kalkulatory.setText(kalkulatoryText);
    }

    public void ladujDrugaAktywnosc1(View view) {
        startActivity(new Intent(ActivityCoPowinienesWiedziec.this, DrugaAktywnosc.class));
    }
}
