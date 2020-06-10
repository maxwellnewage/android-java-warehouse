package ar.com.maxwell.android_warehouse.bitmap_b64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import androidx.annotation.Nullable;
import ar.com.maxwell.android_warehouse.BaseActivity;
import ar.com.maxwell.android_warehouse.R;

public class BitmapB64Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_b64);

        findViewById(R.id.btB64ToBitmap).setOnClickListener(view -> B64ToBitmap("blabla"));
        findViewById(R.id.btBitmapToB64).setOnClickListener(
                view -> bitmapToB64(Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)));
    }

    private String bitmapToB64(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    private Bitmap B64ToBitmap(String encodedImage) {
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
