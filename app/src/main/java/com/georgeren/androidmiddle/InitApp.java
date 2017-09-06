package com.georgeren.androidmiddle;

import android.app.Application;

import com.georgeren.androidmiddle.scope.ActivityComponent;
import com.georgeren.androidmiddle.scope.DaggerActivityComponent;
import com.georgeren.androidmiddle.scope.UserModule;
import com.georgeren.androidmiddle.scope2.dagger.compoent.AppComponent;
import com.georgeren.androidmiddle.scope2.dagger.compoent.DaggerAppComponent;
import com.georgeren.androidmiddle.scope2.dagger.module.AppModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by georgeRen on 2017/8/29.
 */

public class InitApp extends Application{
    ActivityComponent activityComponent;

    private AppComponent mAppComponent;// 全局就这一个AppComponent，实现了所谓的单例。

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        activityComponent = DaggerActivityComponent.builder().userModule(new UserModule()).build();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
    public ActivityComponent getActivityComponent(){
        return activityComponent;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
