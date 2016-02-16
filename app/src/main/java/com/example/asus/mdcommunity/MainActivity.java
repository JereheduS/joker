package com.example.asus.mdcommunity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.asus.mdcommunity.adapter.MyFragmentAdapter;
import com.example.asus.mdcommunity.fragment.ActivityFragment;
import com.example.asus.mdcommunity.fragment.BusinessFragment;
import com.example.asus.mdcommunity.fragment.NoticeFragment;
import com.example.asus.mdcommunity.fragment.StoreFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
     * 定义变量
     */
    private List<Fragment> myFragments;
    private List<String>   myTitles;
    private BusinessFragment businessFragment;
    private ActivityFragment activityFragment;
    private NoticeFragment   noticeFragment;
    private StoreFragment    storeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        initView();
    }

    private void initView(){
        //
        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    //切换相应 Fragment 等操作
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return false;
                }
            });
        }

        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("社联");
        toolbar.setNavigationIcon(R.mipmap.iconfont_gengduo);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        // init tablayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);                  //设置tab模式，当前为系统默认模式

        // init tabTitle
        initTabTitles();

        // init Fragment
        initFragment();

        // init viewpager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),myFragments,myTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initTabTitles() {
        myTitles = new ArrayList<>();
        myTitles.add("商店");
        myTitles.add("交易");
        myTitles.add("活动");
        myTitles.add("公告");
    }

    private void initFragment(){
        myFragments = new ArrayList<>();

        if (businessFragment == null){
            businessFragment = new BusinessFragment();
        }
        if (noticeFragment == null){
            noticeFragment = new NoticeFragment();
        }
        if (storeFragment == null){
            storeFragment = new StoreFragment();
        }
        if (activityFragment == null){
            activityFragment = new ActivityFragment();
        }
        myFragments.add(storeFragment);
        myFragments.add(businessFragment);
        myFragments.add(activityFragment);
        myFragments.add(noticeFragment);
    }
}
