package ar.com.maxwell.android_warehouse.camera.androidx.firebase;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.camera.core.ImageProxy;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnFaceDetection;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnImageProcess;
import ar.com.maxwell.android_warehouse.commons.Utils;

@androidx.camera.core.ExperimentalGetImage
public class FirebaseFaceDetectorActivity extends FirebaseDetectorActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseHandler = new FirebaseHandler(DetectionType.SOFT_FACE);
    }

    @Override
    public void processImage(ImageProxy imageProxy, OnImageProcess onImageProcess) {
        Image mediaImage = imageProxy.getImage();
        byte[] data = Utils.YUV_420_888toNV21(mediaImage);
        int rotation = imageProxy.getImageInfo().getRotationDegrees();

        firebaseHandler.processFace(mediaImage, rotation, face -> {
            Rect rect = face.getBoundingBox();
            byte[] finalData = Utils.NV21toJPEG(data, mediaImage.getWidth(), mediaImage.getHeight());
            Bitmap bmpFrame = Utils.getBitmapFromByteArray(finalData);
            Bitmap bitmap = firebaseHandler.cropFace(bmpFrame, rect);

            runOnUiThread(() -> ivPreview.setImageBitmap(bitmap));

            bmpFrame.recycle();
        }, onImageProcess);
    }
}
