package com.example.asus.mdcommunity.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asus.mdcommunity.R;
import com.example.asus.mdcommunity.adapter.MyRecyclerViewAdapter;
import com.example.asus.mdcommunity.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by asus on 2016/2/16.
 */
public class StoreFragment extends Fragment {
    /*
     *  定义变量
     */
    private List<String> myItems;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isLoadingMore = true;

    /*
     *  声明组件
     */
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_fragment,null);

        // initView
        initView(view);

        return view;
    }

    private void initView(View view){

        //
        initItems();

        //
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);
        final MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),myItems);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                int totalItemCount = adapter.getItemCount();
                //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
                // dy>0 表示向下滑动
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
//                    if (isLoadingMore) {
//                        Log.d("", "ignore manually update!");
//                    } else {
//                        loadPage();//这里多线程也要手动控制isLoadingMore
//                        isLoadingMore = false;
//                    }
                    myItems.add("bbbb");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        swipeRefresh.setColorSchemeResources(R.color.accent_color);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                                 reflesh();
                myItems.add("iiii");
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }
        });

    }
    private void initItems(){
        myItems = new ArrayList<>();
        myItems.add("11");
        myItems.add("12");
        myItems.add("13");
        myItems.add("14");
        myItems.add("15");
        myItems.add("16");
        myItems.add("17");
        myItems.add("18");
        myItems.add("19");
        myItems.add("10");
        myItems.add("21");
        myItems.add("22");
        myItems.add("23");
    }
}
