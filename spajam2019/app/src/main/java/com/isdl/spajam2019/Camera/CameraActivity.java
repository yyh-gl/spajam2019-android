package com.isdl.spajam2019.Camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;

public class CameraActivity extends AppCompatActivity implements CameraContract.View {
    private CameraDevice mCameraDevice;
    private TextureView mTextureView;
    private Handler mBackgroundHandler = new Handler();
    private CameraCaptureSession mCaptureSession = null;

    private CaptureRequest.Builder mPreviewRequestBuilder;
    private CaptureRequest mPreviewRequest;

    @Inject
    CameraPresenter cameraPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        DaggerActivityComponent.builder()
                .appComponent(((Spajam2019Application) getApplicationContext())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        mTextureView = (TextureView) findViewById(R.id.texture);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                // 先ほどのカメラを開く部分をメソッド化した
                openCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

        Button capture = (Button) findViewById(R.id.button_capture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mCaptureSession.stopRepeating(); // プレビューの更新を止める
                    if (mTextureView.isAvailable()) {
                        File file = new File(getFilesDir(), "surface_text.jpg");
                        FileOutputStream fos = new FileOutputStream(file);
                        Bitmap bitmap = mTextureView.getBitmap();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                        fos.close();
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String selectedCameraId = "";
        try {
            selectedCameraId = manager.getCameraIdList()[0];

            // https://github.com/googlesamples/android-Camera2Basic/blob/5dad16c103715b5e7e3c001cc5f6067f8d23f29e/Application/src/main/java/com/example/android/camera2basic/Camera2BasicFragment.java#L499
            // あたりにあるのですが、顔用カメラを使いたくないなどがあれば、CameraCharacteristicsを経由して確認可能
            //            CameraCharacteristics characteristics
            //                    = manager.getCameraCharacteristics(selectedCameraId);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        try {
            manager.openCamera(selectedCameraId, mStateCallback, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private final CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            createCameraPreviewSession();
        }

        @Override
        public void onDisconnected(CameraDevice cameraDevice) {
            cameraDevice.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(CameraDevice cameraDevice, int error) {
            cameraDevice.close();
            mCameraDevice = null;
        }

    };

    private void createCameraPreviewSession() {
        SurfaceTexture texture = mTextureView.getSurfaceTexture();
        texture.setDefaultBufferSize(320, 240); // 自分の手元のデバイスで決めうちしてます
        Surface surface = new Surface(texture);

        try {
            mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mPreviewRequestBuilder.addTarget(surface);
            mPreviewRequest = mPreviewRequestBuilder.build();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        try {
            mCameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    // カメラがcloseされている場合
                    if (null == mCameraDevice) {
                        return;
                    }

                    mCaptureSession = session;

                    try {
                        session.setRepeatingRequest(mPreviewRequest, null, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {

                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
