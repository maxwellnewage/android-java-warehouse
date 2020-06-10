package ar.com.maxwell.android_warehouse.core;

import java.util.ArrayList;
import java.util.List;

import ar.com.maxwell.android_warehouse.MainActivity;
import ar.com.maxwell.android_warehouse.storage.StorageActivity;

public class Utils {
    public static ArrayList<Demo> getDemoList() {
        ArrayList<Demo> demoList = new ArrayList<>();

        demoList.add(new Demo("Demo Frutas - Gson Preferences Manager", StorageActivity.class));

        return demoList;
    }
}
