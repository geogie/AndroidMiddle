package com.georgeren.retrofit.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by georgeRen on 2017/8/30.
 */

public interface IBaseView<T> {
    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}
