package ar.com.maxwell.android_warehouse.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AdvicesVPAdapter extends PagerAdapter {
    private Context context;
    ArrayList<Advice> advices;

    public AdvicesVPAdapter(Context context, ArrayList<Advice> advices) {
        this.context = context;
        this.advices = advices;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup view;

        Advice advice = advices.get(position);
        view = (ViewGroup) inflater.inflate(advice.getLayout(), container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return advices.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
