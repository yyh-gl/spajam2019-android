package com.isdl.spajam2019.Main.Fragment.Cross;

import android.app.Activity;
import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.isdl.spajam2019.Main.MainActivity;
import com.isdl.spajam2019.Models.CrossMusic;
import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.Services.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CrossPresenter implements CrossContract.Presenter {
    Application app;
    ApiService apiService;
    CrossContract.View view;
    MediaPlayer mediaPlayer;
    private ArrayList<Integer> crossMusicIdList = new ArrayList<Integer>();
    private ArrayList<Integer> musicIdList = new ArrayList<Integer>();


    @Inject
    public CrossPresenter(Application app, ApiService apiService, CrossContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }


    public void getPossessedCrossMusic(int userid) {
        apiService.getCrossMusic(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<CrossMusic>>() {
                    @Override
                    public void onSuccess(List<CrossMusic> crossMusics) {
                        musicIdList = new ArrayList<Integer>();
                        crossMusicIdList = new ArrayList<Integer>();
                        for(int i=0;i<crossMusics.size();i++){
                            musicIdList.add(crossMusics.get(i).getId());
                            crossMusicIdList.add(crossMusics.get(i).getUserCrossMusicId());
                        }
                        view.setAdapter(crossMusics);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }

    @Override
    public void audioPlay(Activity activity,int position) {
        if (mediaPlayer == null) {
            // audio ファイルを読出し
            if (audioSetup(activity,position)) {
                Toast.makeText(activity.getApplication(), "Rread audio file", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity.getApplication(), "Error: read audio file", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            // 繰り返し再生する場合
            mediaPlayer.stop();
            mediaPlayer.reset();
            // リソースの解放
            mediaPlayer.release();
        }

        // 再生する
        mediaPlayer.start();

        // 終了を検知するリスナー
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("debug", "end of audio");
                audioStop();
            }
        });

    }

    private boolean audioSetup(Activity activity,int position) {
        boolean fileCheck = false;

        // インタンスを生成
        mediaPlayer = new MediaPlayer();

        //音楽ファイル名, あるいはパス
        String filePath = musicIdList.get(position)+".mp3";

        // assetsから mp3 ファイルを読み込み
        try (AssetFileDescriptor afdescripter = activity.getAssets().openFd(filePath)) {
            // MediaPlayerに読み込んだ音楽ファイルを指定
            mediaPlayer.setDataSource(afdescripter.getFileDescriptor(),
                    afdescripter.getStartOffset(),
                    afdescripter.getLength());
            // 音量調整を端末のボタンに任せる
            activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            fileCheck = true;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return fileCheck;
    }

    @Override
    public void audioStop() {
        // 再生終了
        mediaPlayer.stop();
        // リセット
        mediaPlayer.reset();
        // リソースの解放
        mediaPlayer.release();

        mediaPlayer = null;
    }

    public void deleteCrossMusic(int position) {
        apiService.deleteCrossMusic(crossMusicIdList.get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        getPossessedCrossMusic(MainActivity.getUserId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }

    public void acceptCrossMusic(int position) {
        apiService.acceptCrossMusic(MainActivity.getUserId(),crossMusicIdList.get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        getPossessedCrossMusic(MainActivity.getUserId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }

}
