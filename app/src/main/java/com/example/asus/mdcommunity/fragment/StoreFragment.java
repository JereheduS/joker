package com.example.asus.mdcommunity.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.mdcommunity.R;
import com.example.asus.mdcommunity.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;


/**
 * Created by asus on 2016/2/16.
 */
public class StoreFragment extends Fragment {

    /*
     *  声明组件
     */
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_fragment,null);

        // initView
        initView(view);

        return view;
    }

    private void initView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),new ArrayList<String>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
