package com.isdl.spajam2019.Camera;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import javax.inject.Inject;

public class CameraPermissionPresenter {
    private Application app;
    private CameraPermissionContract.View view;

    private final int REQUEST_CAMERA_PERMISSION = 2000;

    @Inject
    public CameraPermissionPresenter(Application app, CameraPermissionContract.View view) {
        this.app = app;
        this.view = view;
    }

    public void checkPermission(Activity activity) {
        // 既に許可している
        if (ContextCompat.checkSelfPermission(app.getApplicationContext(),
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            startCameraActivity(activity);
        }
        // 拒否していた場合
        else {
            requestCameraPermission(activity);
        }
    }

    public void requestCameraPermission(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            Toast toast = Toast.makeText(activity,
                    "許可されないとアプリが実行できません", Toast.LENGTH_SHORT);
            toast.show();

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA,},
                    REQUEST_CAMERA_PERMISSION);
        }

    }


    public void startCameraActivity(Activity activity) {
        Intent intent = new Intent(app.getApplicationContext(), CameraActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
