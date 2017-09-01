package com.georgeren.retrofit.injector.component;

import com.georgeren.retrofit.injector.module.MainModule;
import com.georgeren.retrofit.module.MainActivity;

import dagger.Component;

/**
 * Created by georgeRen on 2017/8/30.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity view);
}
