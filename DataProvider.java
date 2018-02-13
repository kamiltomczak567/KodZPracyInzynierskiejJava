package com.example.kamil.my_application;

/**
 * Created by K on 2016-02-09.
 */
public class DataProvider {
    private String nazwa;
    private String bialko;
    private String tluszcz;
    private String weglowodany;

    public String kcal;

    String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    String getWeglowodany() {
        return weglowodany;
    }

    public void setWeglowodany(String weglowodany) {
        this.weglowodany = weglowodany;
    }

    String getTluszcz() {
        return tluszcz;
    }

    public void setTluszcz(String tluszcz) {
        this.tluszcz = tluszcz;
    }

    String getBialko() {
        return bialko;
    }

    public void setBialko(String bialko) {
        this.bialko = bialko;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    DataProvider(String nazwa, String kcal, String bialko, String tluszcz, String weglowodany) {
        this.nazwa = nazwa;
        this.kcal = kcal;
        this.bialko = bialko;
        this.tluszcz = tluszcz;
        this.weglowodany = weglowodany;
    }

}
