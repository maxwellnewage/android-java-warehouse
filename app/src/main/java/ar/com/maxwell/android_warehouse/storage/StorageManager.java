package ar.com.maxwell.android_warehouse.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StorageManager {
    private Context context;
    private final String prefsName = "ANDROID_WH_PREFS";
    private final String prefsFruits = "ANDROID_WH_FRUITS_PREFS";
    private Type fruitType;
    private static StorageManager storageManager;

    // Patrón singleton para trabajar con una única instancia del storage
    public static StorageManager getInstance(Context context) {
        if(storageManager == null) {
            storageManager = new StorageManager(context);
        }

        return storageManager;
    }

    StorageManager(Context context) {
        this.context = context;

        fruitType = new TypeToken<List<Fruit>>() {}.getType();
    }

    public List<Fruit> getFruits() {
        SharedPreferences prefs = context.getSharedPreferences(prefsName, MODE_PRIVATE);
        String jsonOfflinePlaces = prefs.getString(prefsFruits, "");

        if(jsonOfflinePlaces.isEmpty()){
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(jsonOfflinePlaces, fruitType);
        }
    }

    public void addFruit(Fruit fruit) {
        List<Fruit> fruitList = getFruits();

        fruitList.add(fruit);

        Gson gson = new Gson();
        String jsonObj = gson.toJson(fruitList);

        SharedPreferences.Editor editor = context.getSharedPreferences(prefsName, MODE_PRIVATE).edit();
        editor.putString(prefsFruits, jsonObj);
        editor.apply();
    }
}
