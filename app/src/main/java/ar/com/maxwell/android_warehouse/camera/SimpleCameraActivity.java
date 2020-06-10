package ar.com.maxwell.android_warehouse.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import ar.com.maxwell.android_warehouse.BaseActivity;
import ar.com.maxwell.android_warehouse.R;

public class SimpleCameraActivity extends BaseActivity {
    ImageView ivPhoto;
    private static final int TAKE_PICTURE = 1;
    private static final int REQ_PERM_CODE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_camera);

        requestPermission();

        ivPhoto = findViewById(R.id.ivPhoto);

        findViewById(R.id.btTakePicture).setOnClickListener(view -> takePicture());
        ivPhoto.setOnClickListener(view -> takePicture());
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQ_PERM_CODE);
    }

    private void takePicture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == TAKE_PICTURE) {
            Bitmap selectedImage;

            try {
                selectedImage = (Bitmap) data.getExtras().get("data");
            } catch (Exception e) {
                showOKDialog("Ocurrió un error en la carga de la imagen.");
                return;
            }

            ivPhoto.setImageBitmap(selectedImage);
        }
    }
}
