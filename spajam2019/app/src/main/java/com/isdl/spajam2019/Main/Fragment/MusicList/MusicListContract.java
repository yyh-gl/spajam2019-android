package com.isdl.spajam2019.Main.Fragment.MusicList;

import com.isdl.spajam2019.Models.User;

import java.util.List;

public interface MusicListContract {
    interface View {

    }

    interface Presenter {
        List<User> createDataset();
    }
}
