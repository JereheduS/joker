package com.example.asus.mdcommunity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asus.mdcommunity.activity.LoginActivity;
import com.example.asus.mdcommunity.activity.UserInfoActivity;
import com.example.asus.mdcommunity.adapter.MyFragmentAdapter;
import com.example.asus.mdcommunity.fragment.ActivityFragment;
import com.example.asus.mdcommunity.fragment.BusinessFragment;
import com.example.asus.mdcommunity.fragment.NoticeFragment;
import com.example.asus.mdcommunity.fragment.StoreFragment;
import com.example.asus.mdcommunity.util.DpAndPxUtil;
import com.kogitune.activity_transition.ActivityTransitionLauncher;

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

    private boolean isOpen;     // 判断fab是否展开

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置转场动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);

//        getWindow().setEnterTransition(new Explode());
//        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_main);

        // 初始化视图
        initView();
    }

    private void initView(){
        // 侧边栏
        final DrawerLayout mDrawerLayout  = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView     navigationView = (NavigationView) findViewById(R.id.navigation);
        LinearLayout       header_view    = (LinearLayout) findViewById(R.id.header_view);
        header_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    //切换相应 Fragment 等操作
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();

                    Intent intent ;
                    switch (menuItem.getItemId()){
                        case R.id.nav_info:
//                            intent = new Intent(MainActivity.this, UserInfoActivity.class);
//                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
//                            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                    activity, transitionView, DetailActivity.EXTRA_IMAGE);
//                            ActivityCompat.startActivity(activity, new Intent(activity, DetailActivity.class),
//                                    options.toBundle());
                            break;
                        case R.id.nav_friends:
                            Toast.makeText(MainActivity.this,"2",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.nav_trends:
                            Toast.makeText(MainActivity.this,"3",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.nav_feedback:
                            Toast.makeText(MainActivity.this,"4",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.nav_version:
                            Toast.makeText(MainActivity.this,"5",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.nav_us:
                            Toast.makeText(MainActivity.this,"6",Toast.LENGTH_LONG).show();
                            break;
                    }
                    return false;
                }
            });
        }

        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("社联");
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_36dp);
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

        // init fab
        initFab();
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

    private void initFab(){
        final ImageView img_shade = (ImageView) findViewById(R.id.shade);
        img_shade.setVisibility(View.GONE);
        final FloatingActionButton fab_menu,shopping_car,add_business,add_activity,add_notice;
        fab_menu     = (FloatingActionButton) findViewById(R.id.fab_menu);
        shopping_car = (FloatingActionButton) findViewById(R.id.shopping_car);
        add_business = (FloatingActionButton) findViewById(R.id.add_business);
        add_activity = (FloatingActionButton) findViewById(R.id.add_activity);
        add_notice   = (FloatingActionButton) findViewById(R.id.add_notice);

        img_shade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen){
                    ObjectAnimator animator0 = ObjectAnimator.ofFloat(shopping_car,"translationY",DpAndPxUtil.dip2px(MainActivity.this,-60),DpAndPxUtil.dip2px(MainActivity.this,0));
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(add_business, "translationY", DpAndPxUtil.dip2px(MainActivity.this, -115), DpAndPxUtil.dip2px(MainActivity.this, -0));
                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(add_activity, "translationY", DpAndPxUtil.dip2px(MainActivity.this, -170), DpAndPxUtil.dip2px(MainActivity.this, 0));
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(add_notice, "translationY", DpAndPxUtil.dip2px(MainActivity.this, -225), DpAndPxUtil.dip2px(MainActivity.this, 0));
                    ObjectAnimator animator4 = ObjectAnimator.ofFloat(fab_menu, "rotation", 135f, 0f);
                    ObjectAnimator animator5 = ObjectAnimator.ofFloat(img_shade, "alpha", 1f, 0f);
                    AnimatorSet animSet = new AnimatorSet();
                    animSet.play(animator0).with(animator1).with(animator2).with(animator3).with(animator4).with(animator5);
                    animSet.setDuration(250);
                    animSet.start();
                    isOpen = false;
                    img_shade.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_shade.setVisibility(View.GONE);
                        }
                    },250);
                }
            }
        });

        fab_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    if (img_shade.getVisibility() == View.GONE) {
                        img_shade.setVisibility(View.VISIBLE);
                    }
                    ObjectAnimator animator0 = ObjectAnimator.ofFloat(shopping_car, "translationY", DpAndPxUtil.dip2px(MainActivity.this, 0), DpAndPxUtil.dip2px(MainActivity.this, -60));
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(add_business, "translationY", DpAndPxUtil.dip2px(MainActivity.this, 0), DpAndPxUtil.dip2px(MainActivity.this, -115));
                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(add_activity, "translationY", DpAndPxUtil.dip2px(MainActivity.this, 0), DpAndPxUtil.dip2px(MainActivity.this, -170));
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(add_notice, "translationY", DpAndPxUtil.dip2px(MainActivity.this, 0), DpAndPxUtil.dip2px(MainActivity.this, -225));
                    ObjectAnimator animator4 = ObjectAnimator.ofFloat(fab_menu, "rotation", 0f, 135f);
                    ObjectAnimator animator5 = ObjectAnimator.ofFloat(img_shade, "alpha", 0f, 1f);
                    AnimatorSet animSet = new AnimatorSet();
                    animSet.play(animator0).with(animator1).with(animator2).with(animator3).with(animator4).with(animator5);
                    animSet.setDuration(250);
                    animSet.start();
                    isOpen = true;
                } else {
                    ObjectAnimator animator0 = ObjectAnimator.ofFloat(shopping_car, "translationY", DpAndPxUtil.dip2px(MainActivity.this, -60), DpAndPxUtil.dip2px(MainActivity.this, 0));
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(add_business, "translationY", DpAndPxUtil.dip2px(MainActivity.this, -115), DpAndPxUtil.dip2px(MainActivity.this, -0));
                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(add_activity, "translationY", DpAndPxUtil.dip2px(MainActivity.this, -170), DpAndPxUtil.dip2px(MainActivity.this, 0));
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(add_notice, "translationY", DpAndPxUtil.dip2px(MainActivity.this, -225), DpAndPxUtil.dip2px(MainActivity.this, 0));
                    ObjectAnimator animator4 = ObjectAnimator.ofFloat(fab_menu, "rotation", 135f, 0f);
                    ObjectAnimator animator5 = ObjectAnimator.ofFloat(img_shade, "alpha", 1f, 0f);
                    AnimatorSet animSet = new AnimatorSet();
                    animSet.play(animator0).with(animator1).with(animator2).with(animator3).with(animator4).with(animator5);
                    animSet.setDuration(250);
                    animSet.start();
                    isOpen = false;
                    img_shade.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_shade.setVisibility(View.GONE);
                        }
                    }, 250);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_search){
            Toast.makeText(this,"你要搜啥",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
