package ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks;

import com.google.mlkit.vision.face.Face;

public interface OnFaceDetection {
    void onSuccess(Face face);
}
