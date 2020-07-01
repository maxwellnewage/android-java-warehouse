package ar.com.maxwell.android_warehouse.camera.androidx.firebase;

import android.graphics.Bitmap;
import android.media.Image;
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
        firebaseHandler = new FirebaseHandler(DetectionType.BARCODE);
    }

    @Override
    public void processImage(ImageProxy imageProxy, OnImageProcess onImageProcess) {
        Image mediaImage = imageProxy.getImage();
        int rotation = imageProxy.getImageInfo().getRotationDegrees();

        firebaseHandler.processBarcode(mediaImage, rotation, barcode -> {

        }, onImageProcess);
    }
}
