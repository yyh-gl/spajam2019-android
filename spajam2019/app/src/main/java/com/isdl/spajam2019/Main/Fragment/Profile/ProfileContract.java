package com.isdl.spajam2019.Main.Fragment.Profile;

public interface ProfileContract {
    interface View {
        void setUserInfo();

    }

    interface Presenter {
        void getUserInfo(int userId);

    }
}
