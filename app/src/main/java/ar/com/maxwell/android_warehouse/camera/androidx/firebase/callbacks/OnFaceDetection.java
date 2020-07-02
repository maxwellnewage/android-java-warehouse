package ar.com.maxwell.android_warehouse.camera.androidx.firebase.callbacks;

import com.google.firebase.ml.vision.face.FirebaseVisionFace;

public interface OnFaceDetection {
    void onSuccess(FirebaseVisionFace face);
}
