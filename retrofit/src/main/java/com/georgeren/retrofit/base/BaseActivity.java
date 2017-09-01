package com.georgeren.retrofit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by georgeRen on 2017/8/30.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView<T> {
    @Inject
    protected T presenter;
    protected MultiTypeAdapter adapter;
    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjector();
    }

    /**
     * 绑定生命周期
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }
}
