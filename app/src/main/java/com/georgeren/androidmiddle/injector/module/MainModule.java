package com.georgeren.androidmiddle.injector.module;

import com.georgeren.androidmiddle.IMain;
import com.georgeren.androidmiddle.MainActivity;
import com.georgeren.androidmiddle.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by georgeRen on 2017/8/29.
 */
@Module
public class MainModule {
    private final MainActivity view;
    public MainModule(MainActivity view){
        this.view = view;
    }
    @Provides
    public IMain.Presenter providePresenter() {
        return new MainPresenter(view);
    }
}
