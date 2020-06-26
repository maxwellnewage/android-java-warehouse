package ar.com.maxwell.android_warehouse;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ar.com.maxwell.android_warehouse.core.Demo;
import ar.com.maxwell.android_warehouse.core.DemoAdapter;
import ar.com.maxwell.android_warehouse.core.DemoRepository;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvDemos = findViewById(R.id.rvDemos);

        rvDemos.setHasFixedSize(true);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvDemos.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvDemos.setLayoutManager(gridLayoutManager);

//        rvDemos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        ArrayList<Demo> demoList = DemoRepository.getDemoList();

        DemoAdapter adapter = new DemoAdapter(this, demoList);

        rvDemos.setAdapter(adapter);
    }
}
