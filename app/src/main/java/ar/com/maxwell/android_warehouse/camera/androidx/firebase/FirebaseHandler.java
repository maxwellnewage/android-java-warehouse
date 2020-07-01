package ar.com.maxwell.android_warehouse.camera.androidx.firebase;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;

import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnFaceDetection;
import ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks.OnTextDetection;

public class FirebaseHandler {
    public FirebaseVisionFaceDetector faceDetector;
    public FirebaseVisionBarcodeDetector barcodeDetector;
    public FirebaseVisionTextRecognizer textRecognizer;
    private FirebaseVisionImageMetadata metadata;
    private FirebaseVisionImage firebaseVisionImage;

    public FirebaseHandler(DetectionType detectionType) {
        setMetadata();

        switch (detectionType) {
            case OCR:
                initOCR();
                break;
            case SOFT_FACE:
                initFaceSoftDetection();
                break;
            case BARCODE:
                initBarcode();
                break;
            case HARD_FACE:
                initFaceHardDetection();
                break;
        }
    }

    private void initOCR() {
        textRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
    }

    private void initBarcode() {
        barcodeDetector = FirebaseVision.getInstance().getVisionBarcodeDetector();
    }

    private void initFaceSoftDetection() {
        FirebaseVisionFaceDetectorOptions faceOptions = new FirebaseVisionFaceDetectorOptions.Builder()
                .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
                .setClassificationMode(FirebaseVisionFaceDetectorOptions.NO_CLASSIFICATIONS)
                .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                .build();

        initFaceDetector(faceOptions);
    }

    private void setMetadata() {
        metadata = new FirebaseVisionImageMetadata.Builder()
                .setWidth(480)
                .setHeight(360)
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                .build();
    }

    private void initFaceHardDetection() {
        FirebaseVisionFaceDetectorOptions faceOptions = new FirebaseVisionFaceDetectorOptions.Builder()
                .setPerformanceMode(FirebaseVisionFaceDetectorOptions.FAST)
                .setContourMode(FirebaseVisionFaceDetectorOptions.ALL_CONTOURS)
                .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                .build();

        initFaceDetector(faceOptions);
    }

    private void initFaceDetector(FirebaseVisionFaceDetectorOptions faceOptions) {
        faceDetector = FirebaseVision.getInstance().getVisionFaceDetector(faceOptions);
    }

    public void setVisionImageFromByteArray(byte[] frameData) {
        firebaseVisionImage = FirebaseVisionImage.fromByteArray(frameData, metadata);
    }

    public void setVisionImageFromMediaImage(Image mediaImage, int rotation) {
        firebaseVisionImage = FirebaseVisionImage.fromMediaImage(mediaImage, rotation);
    }

    public void processFace(OnFaceDetection detection) {
        faceDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(firebaseVisionFaces -> {
            for (FirebaseVisionFace face : firebaseVisionFaces) {
                // Solo la primer cara
                detection.onSuccess(face);
                break;
            }
        });
    }

    public void processOCR(OnTextDetection detection) {
        textRecognizer.processImage(firebaseVisionImage).addOnSuccessListener(firebaseVisionText -> detection.onSuccess(firebaseVisionText.getText()));
    }

    public void processBarcode(OnTextDetection detection) {
        barcodeDetector.detectInImage(firebaseVisionImage)
                .addOnSuccessListener(barcodes -> {
                    for (FirebaseVisionBarcode barcode : barcodes) {
                        detection.onSuccess(barcode.getRawValue());
                    }
                });
    }

    public Bitmap cutFace(Bitmap fbBmp, Rect box) {
        return Bitmap.createBitmap(fbBmp, box.left, box.top, box.width(), box.height());
    }

    public void closeDetectors() {
        try {
            if (faceDetector != null) faceDetector.close();
            if (barcodeDetector != null) barcodeDetector.close();
            if (textRecognizer != null) textRecognizer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
