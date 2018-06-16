package com.isdl.spajam2019.Camera;

import javax.inject.Inject;

public class CameraPresenter {
    private CameraContract.View view;

    @Inject
    public CameraPresenter(CameraContract.View view) {
        this.view = view;
    }
}
