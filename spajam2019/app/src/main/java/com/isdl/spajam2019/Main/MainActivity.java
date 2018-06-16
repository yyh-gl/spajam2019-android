package com.isdl.spajam2019.Main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Main.Fragment.Cheer.CheerFragment;
import com.isdl.spajam2019.Main.Fragment.Cross.CrossFragment;
import com.isdl.spajam2019.Main.Fragment.MusicList.MusicListFragment;
import com.isdl.spajam2019.Main.Fragment.Profile.ProfileFragment;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private final int REQUEST_PERMISSION = 1000;
    private Handler handler = new Handler();

    private CheerFragment cheeerFragment;
    private CrossFragment crossFragment;
    private MusicListFragment musicListFragment;
    private ProfileFragment profileFragment;

    @Inject
    MainPresenter mainPresenter;

    ActionBar toolBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerActivityComponent.builder()
                .appComponent(((Spajam2019Application) getApplicationContext())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        toolBar = getSupportActionBar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        profileFragment = ProfileFragment.newInstance();
        musicListFragment = MusicListFragment.newInstance();
        crossFragment = CrossFragment.newInstance();
        cheeerFragment = CheerFragment.newInstance();


        if (Build.VERSION.SDK_INT >= 23) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    checkPermission();
                }
            });
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    openFragment(getSupportFragmentManager(), profileFragment);
                    return true;
                case R.id.navigation_music_list:
                    openFragment(getSupportFragmentManager(), musicListFragment);
                    return true;
                case R.id.navigation_cross:
                    openFragment(getSupportFragmentManager(), crossFragment);
                    return true;
                case R.id.navigation_cheer:
                    openFragment(getSupportFragmentManager(), cheeerFragment);
                    return true;
            }
            return false;
        }
    };

    @Override

    protected void onResume() {
        super.onResume();
    }

    public void checkPermission() {
        // 既に許可している
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        }
        // 拒否していた場合
        else {
            requestLocationPermission();
        }
        return;
    }

    // 位置情報サービスの使用許可を求める
    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,}, REQUEST_PERMISSION);
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                // それでも拒否された時の対応
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }
}
