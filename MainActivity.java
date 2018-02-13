package com.example.kamil.my_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    BazaDanych bazaDanych;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bazaDanych = new BazaDanych(this);

        String alert = MainActivity.wylosujKomunikat()

        AlertDialog.Builder mojAlert = new AlertDialog.Builder(this);
        mojAlert.setMessage(alert)
                .setPositiveButton("Dalej..", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Czy wiesz, że ...?").create();
        mojAlert.show();
    }

    public static String wylosujKomunikat(){
        Random random = new Random();
        int losowaWartosc = random.nextInt(10);

        if (losowaWartosc == 0) {
            return "Czy wiesz, że zła dieta, siedzący tryb życia i zwiększona ponad normę masa ciała to czynniki ryzyka chorób nowotworowych? Problem jeszcze mocniej pogłębia picie alkoholu.";
        } else if (losowaWartosc == 1) {
            return "Duże spożycie owoców zmniejsza ryzyko występowania raka jamy ustnej i płuc.";
        } else if (losowaWartosc == 2) {
            return "Spożywanie cebuli zmniejsza ryzyko występowania raka żołądka, efekt ten zmniejsza się jednak gdy cebulę solimy, ponieważ sól kuchenna zwiększa ryzyko występowania tego nowotworu.";
        } else if (losowaWartosc == 3) {
            return "Jajka prosto od kury należy umyć, jak tylko przyniesiemy je do domu, a nie tuż przed użyciem. To zapobiega rozwojowi bakterii i ich rozprzestrzenianiu się w lodówce.";
        } else if (losowaWartosc == 4) {
            return "Proteiny w porównaniu do węglowodanów, pomagają w większym stopniu zmniejszyć uczucie głodu mając najwyższą moc sycącą spośród wszystkich makroskładników diety.";
        } else if (losowaWartosc == 5) {
            return "Aż 92% arbuza stanowi woda, dzięki czemu jest on niskokaloryczny, 100g to 36kcal.";
        } else if (losowaWartosc == 6) {
            return "Orzechy, a odchudzanie \n" + "W małych ilościach orzechy są wręcz zalecane, jako podstawa zdrowej zbilansowanej diety.\n" +
                    "Zawierają dużą ilość białka, błonnika i minerałów.";
        } else if (losowaWartosc == 7) {
            return "Zielona hebrata chroni układ krążenia, zmniejsza ryzyko wystąpienia zawałów, skrzepów i choroby wieńcowej, zapobiega nadciśnieniu i miażdżycy";
        } else if (losowaWartosc == 8) {
            return "Cebula bardzo korzystnie wpływa na nasz układ krążenia. Obniża ciśnienie tętnicze krwi oraz działa przeciwkrzepliwie chroniąc nas przed zawałem serca oraz udarem mózgu.";
        } else {
            return "W suszonych morelach znajdziemy więcej beta-karotenu niż w owocach świeżych. Podobnie jest z marchewką - gotowana marchew dostarcza 2 do 5 razy więcej karotenu niż surowa.";
        }
    }

    public void ladujDAktywnosc(View view) {
        startActivity(new Intent(MainActivity.this, DrugaAktywnosc.class));
    }
}
