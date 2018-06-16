package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.app.Activity;

import com.isdl.spajam2019.Models.Music;

import java.util.List;

public interface MusicListContract {
    interface View {
        void setAdapter(List<Music> possessedMusics);
    }

    interface Presenter {
        void getPossessedCrossMusic(int userid);

        void audioPlay(Activity activity,int position);

        void audioStop();
    }
}
