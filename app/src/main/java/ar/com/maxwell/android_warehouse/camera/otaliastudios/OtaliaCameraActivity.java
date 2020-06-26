package ar.com.maxwell.android_warehouse.camera.otaliastudios;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ar.com.maxwell.android_warehouse.BaseActivity;
import ar.com.maxwell.android_warehouse.R;
import ar.com.maxwell.android_warehouse.commons.Utils;

public class OtaliaCameraActivity extends BaseActivity implements FrameProcessor {
    CameraView cvDetection;
    ImageView ivPreview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otalia_camera);

        cvDetection = findViewById(R.id.cvDetection);
        ivPreview = findViewById(R.id.ivPreview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCamera();
    }

    private void initCamera() {
        cvDetection = findViewById(R.id.cvDetection);
        cvDetection.setLifecycleOwner(this);
        cvDetection.addFrameProcessor(this);
        cvDetection.setFacing(Facing.FRONT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cvDetection.removeFrameProcessor(this);
        cvDetection.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cvDetection.removeFrameProcessor(this);
        cvDetection.close();
    }

    @Override
    public void process(@NonNull Frame frame) {
        Bitmap bitmap = Utils.getImageFromByteArray(frame.getData());

        runOnUiThread(() -> ivPreview.setImageBitmap(bitmap));
    }
}
