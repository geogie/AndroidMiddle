package com.georgeren.retrofit.injector.module;

import com.georgeren.retrofit.module.IMain;
import com.georgeren.retrofit.module.MainActivity;
import com.georgeren.retrofit.module.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by georgeRen on 2017/8/30.
 */
@Module
public class MainModule {
    private final MainActivity view;

    public MainModule(MainActivity view) {
        this.view = view;
    }

    @Provides
    public IMain.Presenter providePresenter() {
        return new MainPresenter(view);
    }
}
