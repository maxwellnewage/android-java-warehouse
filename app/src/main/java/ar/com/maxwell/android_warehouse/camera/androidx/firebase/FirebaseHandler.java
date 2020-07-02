package ar.com.maxwell.android_warehouse.camera.androidx.firebase;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.face.Face;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.util.List;

import androidx.annotation.NonNull;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnFaceDetection;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnImageProcess;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnTextDetection;

public class FirebaseHandler {
    FirebaseVisionFaceDetectorOptions faceOptions;

    public FirebaseHandler(DetectionType detectionType) {
        switch (detectionType) {
            case SOFT_FACE:
                setFaceSoftDetectionOptions();
                break;
            case HARD_FACE:
                setFaceHardDetectionOptions();
                break;
        }
    }

    private void setFaceSoftDetectionOptions() {
        faceOptions = new FirebaseVisionFaceDetectorOptions.Builder()
                .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
                .setClassificationMode(FirebaseVisionFaceDetectorOptions.NO_CLASSIFICATIONS)
                .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                .build();
    }

    private void setFaceHardDetectionOptions() {
        faceOptions = new FirebaseVisionFaceDetectorOptions.Builder()
                .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
                .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
                .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                .build();
    }

    public void processFace(Image mediaImage, int rotation, OnFaceDetection detection, OnImageProcess onImageProcess) {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromMediaImage(mediaImage, rotation);

        FirebaseVision.getInstance()
                .getVisionFaceDetector(faceOptions)
                .detectInImage(firebaseVisionImage)
                .addOnSuccessListener(firebaseVisionFaces -> {
                    for(FirebaseVisionFace face : firebaseVisionFaces) {
                        detection.onSuccess(face);
                        break;
                    }
                })
                .addOnFailureListener(e -> Log.e("fail", e.getMessage()));
    }

    public void processBarcode(Image mediaImage, int rotation, OnTextDetection detection, OnImageProcess onImageProcess) {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromMediaImage(mediaImage, rotation);

        FirebaseVision.getInstance()
                .getVisionBarcodeDetector()
                .detectInImage(firebaseVisionImage)
                .addOnSuccessListener(barcodes -> {
                    for (FirebaseVisionBarcode barcode : barcodes) {
                        detection.onSuccess(barcode.getRawValue());
                    }
                })
                .addOnFailureListener(e -> Log.e("fail", e.getMessage()));
    }

    public void processOCR(Image mediaImage, int rotation, OnTextDetection detection, OnImageProcess onImageProcess) {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromMediaImage(mediaImage, rotation);

        FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer()
                .processImage(firebaseVisionImage)
                .addOnSuccessListener(firebaseVisionText -> detection.onSuccess(firebaseVisionText.getText()))
                .addOnFailureListener(e -> Log.e("fail", e.getMessage()));
    }

    public Bitmap cropFace(Bitmap fbBmp, Rect box) {
        return Bitmap.createBitmap(fbBmp, box.left, box.top, box.width(), box.height());
    }

    public void closeDetectors() {
    }
}
