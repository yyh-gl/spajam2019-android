package com.isdl.spajam2019.Recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.isdl.spajam2019.R;

import javax.inject.Inject;

public class RecyclerActivity extends AppCompatActivity {

    @Inject
    RecyclerPresenter recyclerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
    }
}
