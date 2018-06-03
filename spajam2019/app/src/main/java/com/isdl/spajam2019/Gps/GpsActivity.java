package com.isdl.spajam2019.Gps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.isdl.spajam2019.R;

import javax.inject.Inject;

public class GpsActivity extends AppCompatActivity {

    @Inject
    GpsPermissionPresenter gpsPermissionPresenter;
    GpsPresenter gpsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);


    }


}
