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
 * http://blog.csdn.net/u012702547/article/details/52213706
 * Inject如果简单声明两个user，user2则两个对象。User构造加Inject构建，目标类加Inject声明，component类加Component
 * 全局单例：加Singleton，在component加Singleton，在User类上加Singleton，这样user和user2是同一个。如果是module，同理。
 * 局部单例：Scope 自定义注解，替换Singleton，component 替换Singleton 写入共享成员，module中替换Singleton，在Application类中初始化 component
 *  ScopeActivity和TestActivity1共享类User，TestActivity2自己用另外一个User对象。实现了局部单例，共享。
 */

public class ScopeActivity extends AppCompatActivity{
    @Inject
    User user;
    @Inject
    User user2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope);
        ((InitApp)getApplication()).getActivityComponent().inject(this);

        Logger.d("user:"+user);
        Logger.d("user:"+user2);
        startActivity(new Intent(this,TestActivity1.class));
    }
}
