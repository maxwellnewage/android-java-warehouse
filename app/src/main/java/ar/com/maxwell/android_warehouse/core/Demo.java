package ar.com.maxwell.android_warehouse.core;

import android.os.Bundle;

public class Demo {
    private String name;
    private Class activity;
    private Bundle bundle;

    public Demo(String name, Class activity) {
        this.name = name;
        this.activity = activity;
    }

    public Demo(String name, Class activity, Bundle bundle) {
        this.name = name;
        this.activity = activity;
        this.bundle = bundle;
    }

    public String getName() {
        return name;
    }

    public Class getActivity() {
        return activity;
    }

    public Bundle getBundle() {
        return bundle;
    }
}
