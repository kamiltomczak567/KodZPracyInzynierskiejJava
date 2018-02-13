package com.example.kamil.my_application;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


/**
 * Created by K on 2016-01-20.
 */
public class BazaDanych extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BazaDanych.db";
    ///TABELA SKLADNIKI//////
    public static final String TABLE_NAME = "Skladniki";
    public static final String ID = "_id";
    public static final String NAZWA = "NAZWA";
    public static final String Kcal = "Kcal";
    public static final String Bialko = "Bialko";
    public static final String Tluszcz = "Tluszcz";
    public static final String Weglowodany = "Weglowodany";
    ///TABELA SNIADANIE/////////
    public static final String TABLE_NAME2 = "Sniadania";
    public static final String ID2 = "_id";
    public static final String Nazwasniadania = "Nazwasniadania";
    public static final String Skladniki = "Skladniki";
    public static final String Przygotowanie = "Przygotowanie";
    public static final String Sniadaniekcal = "Sniadaniekcal";
    ///TABELA DRUGIE SNIADANIE/////////
    public static final String TABLE_NAME3 = "Sniadaniadrugie";
    public static final String ID3 = "_id";
    public static final String Nazwasniadaniadrugiego = "Nazwasniadaniadrugiego";
    public static final String Skladnikidwa = "Skladnikidwa";
    public static final String Przygotowaniedwa = "Przygotowaniedwa";
    public static final String Sniadaniekcaldwa = "Sniadaniekcaldwa";
    ///TABELA OBIAD/////////
    public static final String TABLE_NAME4 = "Obiad";
    public static final String ID4 = "_id";
    public static final String Nazwaobiad = "Nazwaobiad";
    public static final String Skladnikiobiad = "Skladnikiobiad";
    public static final String Przygotowanieobiad = "Przygotowanieobiad";
    public static final String Obiadkcal = "Obiadkcal";
    ///TABELA PODWIECZOREK/////////
    public static final String TABLE_NAME5 = "Podwieczorek";
    public static final String ID5 = "_id";
    public static final String Nazwapodwieczorek = "Nazwapodwieczorek";
    public static final String Skladnikipodwieczorek = "Skladnikipodwieczorek";
    public static final String Przygotowaniepodwieczorek = "Przygotowaniepodwieczorek";
    public static final String Podwieczorekkcal = "Podwieczorekkcal";
    ///TABELA KOLACJA/////////
    public static final String TABLE_NAME6 = "Kolacja";
    public static final String ID6 = "_id";
    public static final String Nazwakolacja = "Nazwakolacja";
    public static final String Skladnikikolacja = "Skladnikikolacja";
    public static final String Przygotowaniekolacja = "Przygotowaniekolacja";
    public static final String Kolacjakkcal = "Kolacjakkcal";
    public static String PACKAGE_NAME;

    private static final int DATABASE_VERSION = 1;

    private static String DB_PATH;

    private static String DB_NAME = "BazaDanych.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public BazaDanych(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        DB_PATH = "/data/data/" + DrugaAktywnosc.PACKAGE_NAME + "/databases/";

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    boolean insertData(String nazwa, String kcal, String bialko, String tluszcz, String weglowodany) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAZWA, nazwa);
        contentValues.put(Kcal, kcal);
        contentValues.put(Bialko, bialko);
        contentValues.put(Tluszcz, tluszcz);
        contentValues.put(Weglowodany, weglowodany);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    /////////////////////////pobierz z bazy danych ////////////////////////////////
    Cursor pobierzInformacje(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {NAZWA, Kcal, Bialko, Tluszcz, Weglowodany};
        cursor = db.query(TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    String pobierzSniadania(final Integer minimumKcal, final Integer maximumKcal){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] kolumny = {ID2, Nazwasniadania, Skladniki, Przygotowanie, Sniadaniekcal};

        Cursor cursor = db.query(TABLE_NAME2, kolumny, Sniadaniekcal + " >= '" + minimumKcal + "'" + " AND " + Sniadaniekcal + " <= '" + maximumKcal + "'", null, null, null, "Random()" + "LIMIT 1");
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            String nazwasniadania = cursor.getString(1);
            String skladniki = cursor.getString(2);
            String przygotowanie = cursor.getString(3);
            String sniadaniekcal = cursor.getString(4);
            buffer.append("- - - - - - - -- - - - - - - -" + "\n" + nazwasniadania + "\n" + "- - - - - - - -- - - - - - - -" + "\n" + "\n" + skladniki + "\n" + "\n" + przygotowanie + "\n" + "\n" + "Danie ma: " + sniadaniekcal + " kcal" + "\n");

        }
        return buffer.toString();
    }

    String pobierzIISniadania(final Integer minimumKcal, final Integer maximumKcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] kolumny2 = {ID3, Nazwasniadaniadrugiego, Skladnikidwa, Przygotowaniedwa, Sniadaniekcaldwa};
        Cursor cursor2 = db.query(TABLE_NAME3, kolumny2, Sniadaniekcaldwa + " >= '" + minimumKcal + "'" + " AND " + Sniadaniekcaldwa + " <= '" + maximumKcal + "'", null, null, null, "Random()" + "LIMIT 1");
        StringBuffer buffer2 = new StringBuffer();
        while (cursor2.moveToNext()) {
            String nazwasniadania2 = cursor2.getString(1);
            String skladniki2 = cursor2.getString(2);
            String przygotowanie2 = cursor2.getString(3);
            String sniadaniekcal2 = cursor2.getString(4);
            buffer2.append("- - - - - - - -- - - - - - - -" + "\n" + nazwasniadania2 + "\n" + "- - - - - - - -- - - - - - - -" + "\n" + "\n" + skladniki2 + "\n" + "\n" + przygotowanie2 + "\n" + "\n" + "Danie ma: " + sniadaniekcal2 + " kcal" + "\n");

        }
        return buffer2.toString();
    }

    String pobierzObiad(final Integer minimumKcal, final Integer maximumKcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] kolumny3 = {ID4, Nazwaobiad, Skladnikiobiad, Przygotowanieobiad, Obiadkcal};
        Cursor cursor3 = db.query(TABLE_NAME4, kolumny3, Obiadkcal + ">='" + minimumKcal + "'" + " AND " + Obiadkcal + "<='" + maximumKcal + "'", null, null, null, "Random()" + "LIMIT 1");
        StringBuffer buffer3 = new StringBuffer();
        while (cursor3.moveToNext()) {
            String nazwaobiadu = cursor3.getString(1);
            String skladniki3 = cursor3.getString(2);
            String przygotowanie3 = cursor3.getString(3);
            String obiadkcal3 = cursor3.getString(4);
            buffer3.append("- - - - - - - -- - - - - - - -" + "\n" + nazwaobiadu + "\n" + "- - - - - - - - - - - - - - - -" + "\n" + "\n" + skladniki3 + "\n" + "\n" + przygotowanie3 + "\n" + "\n" + "Danie ma: " + obiadkcal3 + " kcal" + "\n");

        }
        return buffer3.toString();
    }

    String pobierzPodwieczorek(final Integer minimumKcal, final Integer maximumKcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] kolumny4 = {ID5, Nazwapodwieczorek, Skladnikipodwieczorek, Przygotowaniepodwieczorek, Podwieczorekkcal};
        Cursor cursor4 = db.query(TABLE_NAME5, kolumny4, Podwieczorekkcal + " >= '" + minimumKcal + "'" + " AND " + Podwieczorekkcal + " <= '" + maximumKcal + "'", null, null, null, "Random()" + "LIMIT 1");
        StringBuffer buffer4 = new StringBuffer();
        while (cursor4.moveToNext()) {
            String nazwapodwieczorku = cursor4.getString(1);
            String skladniki4 = cursor4.getString(2);
            String przygotowanie4 = cursor4.getString(3);
            String podwieczorekkcal4 = cursor4.getString(4);
            buffer4.append("- - - - - - - -- - - - - - - -" + "\n" + nazwapodwieczorku + "\n" + "- - - - - - - -- - - - - - - -" + "\n" + "\n" + skladniki4 + "\n" + "\n" + przygotowanie4 + "\n" + "\n" + "Danie ma: " + podwieczorekkcal4 + " kcal" + "\n");

        }
        return buffer4.toString();

    }

    String pobierzkolacje(final Integer minimumKcal, final Integer maximumKcal) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] kolumny5 = {ID6, Nazwakolacja, Skladnikikolacja, Przygotowaniekolacja, Kolacjakkcal};
        Cursor cursor5 = db.query(TABLE_NAME6, kolumny5, Kolacjakkcal + ">='" + minimumKcal + "'" + " AND " + Kolacjakkcal + "<='" + maximumKcal + "'", null, null, null, "Random()" + "LIMIT 1");
        StringBuffer buffer5 = new StringBuffer();
        while (cursor5.moveToNext()) {
            String nazwakolacji = cursor5.getString(1);
            String skladniki5 = cursor5.getString(2);
            String przygotowanie5 = cursor5.getString(3);
            String kolacjakcal5 = cursor5.getString(4);
            buffer5.append("- - - - - - - -- - - - - - - -" + "\n" + nazwakolacji + "\n" + "- - - - - - - -- - - - - - - -" + "\n" + "\n" + skladniki5 + "\n" + "\n" + przygotowanie5 + "\n" + "\n" + "Danie ma: " + kolacjakcal5 + " kcal" + "\n");

        }
        return buffer5.toString();

    }
}
