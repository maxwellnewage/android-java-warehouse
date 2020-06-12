package ar.com.maxwell.android_warehouse.viewpager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import ar.com.maxwell.android_warehouse.BaseActivity;
import ar.com.maxwell.android_warehouse.R;

public class SimpleVPActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_vp);

        ViewPager vpAdvices = findViewById(R.id.vpAdvices);

        AdvicesVPAdapter adapter = new AdvicesVPAdapter(this, getAdvices());
        vpAdvices.setAdapter(adapter);
    }

    private ArrayList<Advice> getAdvices() {
        ArrayList<Advice> advices = new ArrayList<>();
        advices.add(new Advice(R.layout.page_human));
        advices.add(new Advice(R.layout.page_notes));
        advices.add(new Advice(R.layout.page_time));

        return advices;
    }
}
