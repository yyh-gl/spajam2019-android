package com.isdl.spajam2019.Main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.DataBase.CrossedUser;
import com.isdl.spajam2019.Main.Fragment.Cheer.CheerFragment;
import com.isdl.spajam2019.Main.Fragment.Cross.CrossFragment;
import com.isdl.spajam2019.Main.Fragment.MusicList.MusicListFragment;
import com.isdl.spajam2019.Main.Fragment.Profile.ProfileFragment;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

import javax.inject.Inject;

import static org.altbeacon.beacon.service.BeaconService.TAG;

public class MainActivity extends AppCompatActivity implements MainContract.View, BeaconConsumer {

    private final int REQUEST_PERMISSION = 1000;
    private Handler handler = new Handler();
    private CrossedUser crossedUser = new CrossedUser();

    private CheerFragment cheeerFragment;
    private CrossFragment crossFragment;
    private MusicListFragment musicListFragment;
    private ProfileFragment profileFragment;

    static BeaconManager beaconManager;
    public static final String IBEACON_FORMAT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";
    static final String BEACON_UUID = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa";

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

        //Fragment作成
        if (cheeerFragment == null) cheeerFragment = CheerFragment.newInstance();
        if (crossFragment == null) crossFragment = CrossFragment.newInstance();
        if (profileFragment == null) profileFragment = ProfileFragment.newInstance();
        if (musicListFragment == null) {
            musicListFragment = MusicListFragment.newInstance();
            openFragment(getSupportFragmentManager(), musicListFragment);
        }


        //Beaconに必要な設定
        beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(IBEACON_FORMAT));

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
        beaconManager.bind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                try {
                    beaconManager.startRangingBeaconsInRegion(region);
                    System.out.println("レンジング開始");
                } catch (RemoteException e) {
                }
                Log.i(TAG, "didEnterRegion");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "didExitRegion");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: " + state);
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("SPAJAM2018", Identifier.parse(BEACON_UUID), null, null));
        } catch (RemoteException e) {
        }


        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                // 検出したビーコンの情報を全部Logに書き出す
                for (Beacon beacon : beacons) {
                    Boolean userFlag = false;//すれ違ったかどうかを判断する
                    Log.d("MyActivity", "UUID:" + beacon.getId1() + ", major:" + beacon.getId2() + ", minor:" + beacon.getId3()+", RSSI:"+beacon.getRssi());
                    for(int i=0;i<crossedUser.crossedUser.size();i++){
                        if(Integer.parseInt(beacon.getId3().toString()) == crossedUser.crossedUser.get(i)){
                            userFlag = true;
                        }
                    }
                    if(userFlag == false){
                        if (beacon.getRssi() >= -50){
                            handler.post(() -> {
                                        Toast toast = Toast.makeText(getApplicationContext(), "すれ違いました", Toast.LENGTH_SHORT);
                                        toast.show();
                                    });
                            crossedUser.crossedUser.add(beacon.getId3().toInt());

                            //すれ違ったapiをたたく
                            mainPresenter.postCrossMusic(2,3);
                        }
                    }
                    userFlag = false;
                }
            }
        });

        Beacon beacon = new Beacon.Builder()
                .setId1("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .setId2("1")
                .setId3("3")
                .setManufacturer(0x004C)
                .build();
        BeaconParser beaconParser = new BeaconParser()
                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24");
        BeaconTransmitter beaconTransmitter = new BeaconTransmitter(getApplicationContext(), beaconParser);

        //送信開始
        beaconTransmitter.startAdvertising(beacon);

    }

    public void stopMonitoringBeaconsInRegion() {   //ビーコンの監視を止める
        try {
            beaconManager.stopRangingBeaconsInRegion(new Region("ISDL", Identifier.parse(BEACON_UUID), null, null));
            System.out.println("レンジング終了");
        } catch (RemoteException e) {
        }

        try {
            beaconManager.stopMonitoringBeaconsInRegion(new Region("ISDL", Identifier.parse(BEACON_UUID), null, null));
            System.out.println("領域監視終了");
        } catch (RemoteException e) {
        }
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
                Toast toast = Toast.makeText(this, "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public static void openFragment(FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
