package com.why.rk1220;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.why.rk1220.bean.Goods;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    List<Goods> list;
    Context context;
    private OnRecyclerViewItemClickListter onRecyclerViewItemClickListter;
    public MyAdapter(List<Goods> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_list, null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.textView.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon()).into(myHolder.imageView);
        // 点击条目删除
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用接口进行删除
                onRecyclerViewItemClickListter.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text01);
            imageView=itemView.findViewById(R.id.img);
        }
    }
    // 接口删除
    public interface OnRecyclerViewItemClickListter {
        void onItemClick(int position);
    }
    // 方法删除
    public void onRecycler(OnRecyclerViewItemClickListter onRecyclerViewItemClickListter) {
        this.onRecyclerViewItemClickListter = onRecyclerViewItemClickListter;
    }

}
