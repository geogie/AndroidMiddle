package com.georgeren.androidmiddle.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by georgeRen on 2017/8/29.
 */

public abstract class BaseActivity <T extends IBasePresenter> extends AppCompatActivity {
    @Inject
    protected T presenter;
    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjector();
    }
}
