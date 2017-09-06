package com.georgeren.androidmiddle.scope2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.georgeren.androidmiddle.scope2.adapter.MainRvAdapter;
import com.georgeren.androidmiddle.scope2.dagger.module.MainModule;

import javax.inject.Inject;

/**
 * Created by georgeRen on 2017/9/6.
 * 参考文章：http://www.jianshu.com/p/11e21bab1067 参考文章下的demo：https://github.com/lightofrain/LocalRxbus
 */

public class Scope2Activity1 extends BaseActivity{
    private static final String TAG = "Scope2Activity1";
    @Inject
    MainRvAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "mAdapter:" + mAdapter.toString());

    }
    @Override
    protected void inject() {
        getBaseComponent().providerMainComponent(new MainModule()).inject(this);
    }
}
