package com.georgeren.androidmiddle.scope2.dagger.compoent;


import com.georgeren.androidmiddle.scope2.annotation.BaseScope;
import com.georgeren.androidmiddle.scope2.dagger.module.BaseModule;
import com.georgeren.androidmiddle.scope2.dagger.module.MainModule;

import dagger.Subcomponent;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
//这里使用针对Activity的作用域
@BaseScope
@Subcomponent(modules = BaseModule.class)
public interface BaseComponent {
    MainComponent providerMainComponent(MainModule module);
}
