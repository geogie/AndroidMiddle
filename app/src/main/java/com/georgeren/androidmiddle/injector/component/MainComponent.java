package com.georgeren.androidmiddle.injector.component;

import com.georgeren.androidmiddle.MainActivity;
import com.georgeren.androidmiddle.injector.module.MainModule;

import dagger.Component;

/**
 * Created by georgeRen on 2017/8/29.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity view);
}
