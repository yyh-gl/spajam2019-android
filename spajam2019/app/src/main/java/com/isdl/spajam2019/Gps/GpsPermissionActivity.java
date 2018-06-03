package com.isdl.spajam2019.Gps;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class GpsPermissionActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSION = 1000;

    @Inject
    GpsPermissionPresenter gpsPermissionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpspermission);
        ((Spajam2019Application) getApplication()).getAppComponent().inject(this);

        // Android 6, API 23以上でパーミッシンの確認
        if (Build.VERSION.SDK_INT >= 23) {
            gpsPermissionPresenter.checkPermission(this);
        } else {
            gpsPermissionPresenter.startLocationActivity(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gpsPermissionPresenter.startLocationActivity(this);

            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }


}
