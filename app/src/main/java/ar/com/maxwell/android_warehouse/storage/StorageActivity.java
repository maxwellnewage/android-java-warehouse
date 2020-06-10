package ar.com.maxwell.android_warehouse.storage;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ar.com.maxwell.android_warehouse.BaseActivity;
import ar.com.maxwell.android_warehouse.R;

public class StorageActivity extends BaseActivity {
    TextView tvFruitList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        EditText etFruit = findViewById(R.id.etFruit);
        tvFruitList = findViewById(R.id.tvFruitList);

        findViewById(R.id.btAddFruit).setOnClickListener(view -> {
            String fruit = etFruit.getText().toString();

            if(fruit.isEmpty()) {
                showOKDialog("No veo ninguna fruta ahi...");
            } else {
                addFruit(fruit);
            }
        });

        showAllFruits();
    }

    private void addFruit(String sFruit) {
        Fruit fruit = new Fruit(sFruit);

        StorageManager.getInstance(StorageActivity.this).addFruit(fruit);

        showOKDialog("Fruta agregada con exito!");

        showAllFruits();
    }

    private void showAllFruits() {
        StringBuilder sFruitList = new StringBuilder();
        List<Fruit> fruitList = StorageManager.getInstance(StorageActivity.this).getFruits();

        for(int i = 0; i < fruitList.size(); i++){
            sFruitList.append((i + 1) + " - " + fruitList.get(i).getName() + "\n");
        }

        tvFruitList.setText(sFruitList.toString());
    }
}
