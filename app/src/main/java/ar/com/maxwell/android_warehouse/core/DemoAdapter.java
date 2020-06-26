package ar.com.maxwell.android_warehouse.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ar.com.maxwell.android_warehouse.R;
import ar.com.maxwell.android_warehouse.commons.Constants;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {
    Context context;
    List<Demo> demoList;

    public DemoAdapter(Context context, List<Demo> demoList) {
        this.context = context;
        this.demoList = demoList;
    }

    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.core_item_demo, viewGroup, false);
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {
        Demo demo = demoList.get(position);

        holder.tvName.setText(demo.getName());

        holder.llDemoContainer.setOnClickListener(view -> runDemo(demo.getActivity(), demo.getBundle()));
    }

    @Override
    public int getItemCount() {
        return demoList.size();
    }

    private void runDemo(Class activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        if (bundle != null) intent.putExtras(bundle);
        context.startActivity(intent);
    }

    static class DemoViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        LinearLayout llDemoContainer;

        DemoViewHolder(@NonNull View v) {
            super(v);

            tvName = v.findViewById(R.id.tvDemoName);
            llDemoContainer = v.findViewById(R.id.llDemoContainer);
        }
    }
}
