package ar.com.maxwell.android_warehouse.camera.androidx.firebase;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.List;

import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnFaceDetection;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnImageProcess;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnTextDetection;

public class FirebaseHandler {
    FaceDetectorOptions faceOptions;

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

//    private void initOCR() {
//        textRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
//    }
//
//    private void initBarcode() {
//        barcodeDetector = FirebaseVision.getInstance().getVisionBarcodeDetector();
//    }

    private void setFaceSoftDetectionOptions() {
        faceOptions = new FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .build();
    }

    private void setFaceHardDetectionOptions() {
        faceOptions = new FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .build();
    }

    public void processFace(Image mediaImage, int rotation, OnFaceDetection detection, OnImageProcess onImageProcess) {
        InputImage inputImage = InputImage.fromMediaImage(mediaImage, rotation);

        FaceDetection.getClient(faceOptions).process(inputImage).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for(Face face : task.getResult()) {
                    detection.onSuccess(face);
                    break;
                }
            }

            onImageProcess.onComplete();
        }).addOnFailureListener(e -> {
            Log.e("fail", e.getMessage());
        });
    }

    public void processBarcode(Image mediaImage, int rotation, OnTextDetection detection, OnImageProcess onImageProcess) {
        InputImage inputImage = InputImage.fromMediaImage(mediaImage, rotation);

//        .addOnCompleteListener(task -> {
//            if(task.isSuccessful()) {
//                for(Barcode barcode : task.getResult()) {
//                    detection.onSuccess(barcode.getRawValue());
//                }
//            }
//
//            onImageProcess.onComplete();
//        })

        BarcodeScanning.getClient().process(inputImage).addOnFailureListener(e -> {
            Log.e("fail", e.getMessage());
        }).addOnSuccessListener(barcodes -> {
            for(Barcode barcode : barcodes) {
                detection.onSuccess(barcode.getRawValue());
            }
        });
    }

//    public void processOCR(OnTextDetection detection) {
//        textRecognizer.processImage(firebaseVisionImage).addOnSuccessListener(firebaseVisionText -> detection.onSuccess(firebaseVisionText.getText()));
//    }
//
//    public void processBarcode(OnTextDetection detection) {
//        barcodeDetector.detectInImage(firebaseVisionImage)
//                .addOnSuccessListener(barcodes -> {
//                    for (FirebaseVisionBarcode barcode : barcodes) {
//                        detection.onSuccess(barcode.getRawValue());
//                    }
//                });
//    }

    public Bitmap cropFace(Bitmap fbBmp, Rect box) {
        return Bitmap.createBitmap(fbBmp, box.left, box.top, box.width(), box.height());
    }

    public void closeDetectors() {
        FaceDetection.getClient(faceOptions).close();
    }
}
