package com.example.kamil.my_application;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by K on 2016-02-17.
 */
public class AdapterDoPrzesuwania extends PagerAdapter {
    private int[] zdjecia = {R.drawable.bezy, R.drawable.lody, R.drawable.pianka, R.drawable.puddingczekoladowy, R.drawable.salatkaowocowa, R.drawable.sernik};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private int pozycja;
    private TextView textView;

    public AdapterDoPrzesuwania(Context ctx) {

        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return zdjecia.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view == (LinearLayout) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.przesuwanie_posilkow, container, false);
        ImageView imageView = item_view.findViewById(R.id.image_view1);
        textView = item_view.findViewById(R.id.image_count);
        imageView.setImageResource(zdjecia[position]);


        pozycja = position;
        textView.setText("Deser: " + (position + 1));
        container.addView(item_view);


        return item_view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
        //  super.destroyItem(container, position, object);
    }
}
