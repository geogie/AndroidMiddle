package com.georgeren.androidmiddle.scope2.dagger.compoent;


import com.georgeren.androidmiddle.scope2.Scope2Activity;
import com.georgeren.androidmiddle.scope2.Scope2Activity1;
import com.georgeren.androidmiddle.scope2.dagger.module.MainModule;

import dagger.Subcomponent;

/**
 * Created by 苏杭 on 2017/5/26 11:19.
 */
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(Scope2Activity activity);
    void inject(Scope2Activity1 activity);
}
