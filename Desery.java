package com.example.kamil.my_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Desery extends AppCompatActivity {
    AdapterDoPrzesuwania adapter;
    ViewPager viewPager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desery);
        viewPager1 = findViewById(R.id.view_pager);
        adapter = new AdapterDoPrzesuwania(this);
        viewPager1.setAdapter(adapter);

    }

    public void ladujObiaddd(View view) {
        startActivity(new Intent(Desery.this, Obiad.class));
    }
}
