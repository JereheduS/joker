package com.example.asus.mdcommunity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.mdcommunity.R;

import java.util.List;

/**
 * Created by asus on 2016/2/16.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> items;

    public MyRecyclerViewAdapter(Context context, List<String> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyler_view_item,parent,false);
        return new MyViewHold(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHold myViewHold = (MyViewHold) holder;

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHold extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public MyViewHold(View itemView) {
            super(itemView);
            textView  = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
