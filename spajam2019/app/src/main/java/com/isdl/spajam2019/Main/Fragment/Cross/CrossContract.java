package com.isdl.spajam2019.Main.Fragment.Cross;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.Models.User;

import java.util.List;

public interface CrossContract {
    interface View {
        void setDataList(List<Music> possessedMusics);

    }

    interface Presenter {
        List<User> createDataset();

    }
}
