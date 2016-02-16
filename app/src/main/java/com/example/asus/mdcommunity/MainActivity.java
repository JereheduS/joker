package com.example.asus.mdcommunity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        initView();
    }

    private void initView(){
        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("社联");
        toolbar.setNavigationIcon(R.drawable.abc_ic_clear_mtrl_alpha);
        setSupportActionBar(toolbar);

    }
}
