package com.georgeren.androidmiddle.scope2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.georgeren.androidmiddle.R;
import com.georgeren.androidmiddle.scope2.adapter.MainRvAdapter;
import com.georgeren.androidmiddle.scope2.dagger.module.MainModule;

import javax.inject.Inject;

/**
 * Created by georgeRen on 2017/9/6.
 */

public class Scope2Activity extends BaseActivity {
    private static final String TAG = "Scope2Activity";
    @Inject
    MainRvAdapter mAdapter;
    @Inject
    MainRvAdapter mAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"mAdapter:"+mAdapter.toString());
        Log.d(TAG,"mAdapter2:"+mAdapter2.toString());
    }

    @Override
    protected void inject() {
        getBaseComponent().providerMainComponent(new MainModule()).inject(this);
        startActivity(new Intent(Scope2Activity.this,Scope2Activity1.class));

    }

}
