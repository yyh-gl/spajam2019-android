package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.app.Activity;
import android.app.Application;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.Services.ApiService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MusicListPresenter implements MusicListContract.Presenter {
    Application app;
    ApiService apiService;
    MusicListContract.View view;
    MediaPlayer mediaPlayer;

    @Inject
    public MusicListPresenter(Application app, ApiService apiService, MusicListContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getPossessedCrossMusic(int userid) {
        apiService.getPossessedMusic(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Music>>() {
                    @Override
                    public void onSuccess(List<Music> possessedMusics) {
                        view.setAdapter(possessedMusics);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }

    @Override
    public void audioPlay(Activity activity) {
        if (mediaPlayer == null) {
            // audio ファイルを読出し
            if (audioSetup(activity)) {
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

    private boolean audioSetup(Activity activity) {
        boolean fileCheck = false;

        // インタンスを生成
        mediaPlayer = new MediaPlayer();

        //音楽ファイル名, あるいはパス
        String filePath = "前前前世.mp3";

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
}
