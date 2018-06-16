package com.isdl.spajam2019.Main;

import com.isdl.spajam2019.Main.Fragment.Cheer.CheerFragment;

/**
 * Created by takayayuuki on 2018/05/25.
 */

public interface MainContract {
    interface View {
        void showToast(String message);

    }

    interface Presenter {
        void openFragment(CheerFragment cheerFragment);

    }
}
