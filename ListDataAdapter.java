package com.example.kamil.my_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K on 2016-02-09.
 */
public class ListDataAdapter extends ArrayAdapter {

    private List list = new ArrayList();

    ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView NAZWA, KCAL, BIALKO, TLUSZCZ, WEGLOWODANY;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAZWA = row.findViewById(R.id.text_nazwa);
            layoutHandler.KCAL = row.findViewById(R.id.text_kcal);
            layoutHandler.BIALKO = row.findViewById(R.id.text_bialko);
            layoutHandler.TLUSZCZ = row.findViewById(R.id.text_tluszcz);
            layoutHandler.WEGLOWODANY = row.findViewById(R.id.text_weglowodany);
            row.setTag(layoutHandler);
        } else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.NAZWA.setText(dataProvider.getNazwa());
        layoutHandler.KCAL.setText(dataProvider.getKcal());
        layoutHandler.BIALKO.setText(dataProvider.getBialko());
        layoutHandler.TLUSZCZ.setText(dataProvider.getTluszcz());
        layoutHandler.WEGLOWODANY.setText(dataProvider.getWeglowodany());
        return row;
    }
}
