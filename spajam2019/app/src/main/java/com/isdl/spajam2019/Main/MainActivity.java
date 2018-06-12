package com.isdl.spajam2019.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.Main.DI.DaggerMainComponent;
import com.isdl.spajam2019.Main.DI.MainModule;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .appComponent(((Spajam2019Application) getApplicationContext())
                        .getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        mainPresenter.apiRequest();
        mainPresenter.apiPost();

        Intent intent = new Intent(this, GpsPermissionActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
