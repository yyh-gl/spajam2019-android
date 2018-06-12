package com.isdl.spajam2019.Gps;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class LocationActivity extends AppCompatActivity implements LocationContract.View {

    @Inject
    GpsPermissionPresenter gpsPermissionPresenter;
    @Inject
    LocationPresenter locationPresenter;

    private TextView textView;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        DaggerActivityComponent.builder()
                .appComponent(((Spajam2019Application) getApplicationContext())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        textView = findViewById(R.id.textView);
        Button buttonStart = findViewById(R.id.button_start);
        Button buttonStop = findViewById(R.id.button_stop);

        buttonStart.setOnClickListener(view -> locationPresenter.startGPS(locationManager));
        buttonStop.setOnClickListener(view -> locationPresenter.stopGPS(locationManager));

    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void enableLocationSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }
}
