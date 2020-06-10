package ar.com.maxwell.android_warehouse.core;

import java.util.ArrayList;
import java.util.List;

import ar.com.maxwell.android_warehouse.MainActivity;

public class Utils {
    public static ArrayList<Demo> getDemoList() {
        ArrayList<Demo> demoList = new ArrayList<>();

        demoList.add(new Demo("Demo de prueba!", MainActivity.class));

        return demoList;
    }
}
