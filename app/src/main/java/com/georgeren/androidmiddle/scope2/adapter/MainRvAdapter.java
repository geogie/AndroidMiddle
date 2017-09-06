package com.georgeren.androidmiddle.scope2.adapter;

import com.georgeren.androidmiddle.scope2.annotation.BaseScope;

import javax.inject.Inject;

/**
 * Created by georgeRen on 2017/9/6.
 */
@BaseScope
public class MainRvAdapter {
    //将Adapter也注入
    @Inject
    public MainRvAdapter() {
    }
}
