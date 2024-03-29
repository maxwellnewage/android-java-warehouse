package ar.com.maxwell.android_warehouse.camera.androidx;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import ar.com.maxwell.android_warehouse.commons.Utils;

@androidx.camera.core.ExperimentalGetImage
public class CustomCameraBackActivity extends CustomCameraActivity {

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
                byte[] data = Utils.YUV_420_888toNV21(image.getImage());
                byte[] finalData = Utils.NV21toJPEG(data, image.getWidth(), image.getHeight());
                Bitmap bitmap = Utils.getBitmapFromByteArray(finalData);

                runOnUiThread(() -> ivPreview.setImageBitmap(bitmap));
            }

            image.close();
        });

        preview.setSurfaceProvider(mPreviewView.createSurfaceProvider());
        Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis);
    }
}
