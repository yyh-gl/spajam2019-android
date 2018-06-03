package com.isdl.spajam2019.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Spajam2019Application) getApplication()).getAppComponent().inject(this);

        Intent intent = new Intent(this, GpsPermissionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mainPresenter.apiRequest();
//        mainPresenter.apiPost();

    }

}
