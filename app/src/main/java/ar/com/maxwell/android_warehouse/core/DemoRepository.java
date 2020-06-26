package ar.com.maxwell.android_warehouse.core;

import java.util.ArrayList;

import ar.com.maxwell.android_warehouse.bitmap_b64.BitmapB64Activity;
import ar.com.maxwell.android_warehouse.camera.androidx.CustomCameraBackActivity;
import ar.com.maxwell.android_warehouse.camera.androidx.CustomCameraFrontActivity;
import ar.com.maxwell.android_warehouse.camera.SimpleCameraActivity;
import ar.com.maxwell.android_warehouse.geolocalization.LocationActivity;
import ar.com.maxwell.android_warehouse.network.CatPhotoActivity;
import ar.com.maxwell.android_warehouse.storage.StorageActivity;
import ar.com.maxwell.android_warehouse.viewpager.SimpleVPActivity;

public class DemoRepository {
    public static ArrayList<Demo> getDemoList() {
        ArrayList<Demo> demoList = new ArrayList<>();

        demoList.add(new Demo("Demo Frutas - Gson Preferences Manager", StorageActivity.class));
        demoList.add(new Demo("Demo Bitmap - Base 64", BitmapB64Activity.class));
        demoList.add(new Demo("Demo Geolocalización", LocationActivity.class));
        demoList.add(new Demo("Demo Simple Camera", SimpleCameraActivity.class));
        demoList.add(new Demo("Retrofit & Glide Demo", CatPhotoActivity.class));
        demoList.add(new Demo("View Pager Demo", SimpleVPActivity.class));
        demoList.add(new Demo("Custom Camera Demo (Back)", CustomCameraBackActivity.class));
        demoList.add(new Demo("Custom Camera Demo (Front)", CustomCameraFrontActivity.class));

        return demoList;
    }
}
