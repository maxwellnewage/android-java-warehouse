package ar.com.maxwell.android_warehouse.camera.androidx.firebase;

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
import ar.com.maxwell.android_warehouse.commons.Utils;

@androidx.camera.core.ExperimentalGetImage
public abstract class FirebaseDetectorActivity extends CustomCameraActivity {

    @Override
    public void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .build();

        imageAnalysis.setAnalyzer(executor, image -> {

            if(image.getImage() != null) {
                processImage(image);
                image.close();
            }

        });

        preview.setSurfaceProvider(mPreviewView.createSurfaceProvider());
        Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
    }

    public abstract void processImage(ImageProxy imageProxy);
}
