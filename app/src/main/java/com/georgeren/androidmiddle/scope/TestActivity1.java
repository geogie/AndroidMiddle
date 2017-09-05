package com.georgeren.androidmiddle.scope;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.georgeren.androidmiddle.InitApp;
import com.georgeren.androidmiddle.R;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Created by georgeRen on 2017/9/5.
 */

public class TestActivity1 extends AppCompatActivity{
    @Inject
    User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope);
        ((InitApp)getApplication()).getActivityComponent().inject(this);
        Logger.d(user);
        startActivity(new Intent(this,TestActivity2.class));
    }
}
