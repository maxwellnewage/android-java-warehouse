package ar.com.maxwell.android_warehouse.camera.androidx.firebase;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import ar.com.maxwell.android_warehouse.camera.androidx.CustomCameraActivity;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnImageProcess;
import ar.com.maxwell.android_warehouse.commons.Utils;

@androidx.camera.core.ExperimentalGetImage
public class FirebaseBarcodeDetectorActivity extends FirebaseDetectorActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ivPreview.setVisibility(View.GONE);
    }

    @Override
    public void processImage(ImageProxy imageProxy, OnImageProcess onImageProcess) {

    }
}
