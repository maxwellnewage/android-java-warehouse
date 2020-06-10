package ar.com.maxwell.android_warehouse.core;

import java.util.ArrayList;
import java.util.List;

import ar.com.maxwell.android_warehouse.MainActivity;
import ar.com.maxwell.android_warehouse.bitmap_b64.BitmapB64Activity;
import ar.com.maxwell.android_warehouse.camera.SimpleCameraActivity;
import ar.com.maxwell.android_warehouse.geolocalization.LocationActivity;
import ar.com.maxwell.android_warehouse.network.CatPhotoActivity;
import ar.com.maxwell.android_warehouse.storage.StorageActivity;

public class DemoRepository {
    public static ArrayList<Demo> getDemoList() {
        ArrayList<Demo> demoList = new ArrayList<>();

        demoList.add(new Demo("Demo Frutas - Gson Preferences Manager", StorageActivity.class));
        demoList.add(new Demo("Demo Bitmap - Base 64", BitmapB64Activity.class));
        demoList.add(new Demo("Demo Geolocalización", LocationActivity.class));
        demoList.add(new Demo("Demo Simple Camera", SimpleCameraActivity.class));
        demoList.add(new Demo("Retrofit & Glide Demo", CatPhotoActivity.class));

        return demoList;
    }
}
