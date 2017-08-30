package com.georgeren.drawer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.georgeren.drawer.R;
import com.georgeren.drawer.util.SettingUtil;

/**
 * Created by georgeRen on 2017/8/30.
 */

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 初始化 夜间\白天 主题
     */
    protected void initTheme() {
        boolean isNightMode = SettingUtil.getInstance().getIsNightMode();
        if (isNightMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
    }
    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled 是否加返回图标(测试无效)
     * @param title 标题，如果 null 则会自动用appName
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);// ToolBar控件替代ActionBar控件
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(homeAsUpEnabled);// 给左上角图标的左边加上一个返回的图标
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
    }
}
