package com.isdl.spajam2019.Main.Fragment.Cross;

import android.app.Activity;

import com.isdl.spajam2019.Models.CrossMusic;
import com.isdl.spajam2019.Models.Music;

import java.util.List;

public interface CrossContract {
    interface View {
        void setAdapter(List<CrossMusic> possessedMusics);

    }

    interface Presenter {
        void getPossessedCrossMusic(int userid);
        void audioPlay(Activity activity,int position);
        void audioStop();
        void deleteCrossMusic(int position);
        void acceptCrossMusic(int position);
    }
}
