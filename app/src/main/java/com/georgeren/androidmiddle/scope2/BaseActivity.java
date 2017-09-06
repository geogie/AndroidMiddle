package com.georgeren.androidmiddle.scope2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.georgeren.androidmiddle.InitApp;
import com.georgeren.androidmiddle.scope2.annotation.BaseScope;
import com.georgeren.androidmiddle.scope2.dagger.compoent.BaseComponent;
import com.georgeren.androidmiddle.scope2.dagger.module.BaseModule;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by georgeRen on 2017/9/6.
 */
@BaseScope
public abstract class BaseActivity extends AppCompatActivity {
    //CompositeDisposable在BaseModule中被提供,并且作用域是BaseScope,所以在该域内该对象都只有一份
    @Inject
    CompositeDisposable mDisposable;
    private BaseComponent mBaseComponent;// 决定了每个子activity 都有一个BaseComponent，实现了activity间互相不干扰

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过父组件AppComponent来注册BaseComponent
        mBaseComponent = ((InitApp) getApplication()).getAppComponent().baseComponent(new BaseModule(this));// 全局单例（app）--》局部单例(每个子activity都拥有一个Component)。
        inject();
    }

    /**
     * 得到BaseComponent
     * @return
     */
    protected BaseComponent getBaseComponent() {
        return mBaseComponent;
    }

    /**
     * 需要子类进行inject
     */
    protected abstract void  inject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //回收CompositeDisposable,因整个BaseScope域都用的同一个,所以能把所有产生的Disposable回收
        mDisposable.dispose();
    }
}
