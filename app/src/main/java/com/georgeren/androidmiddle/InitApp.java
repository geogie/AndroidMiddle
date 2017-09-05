package com.georgeren.androidmiddle;

import android.app.Application;

import com.georgeren.androidmiddle.scope.ActivityComponent;
import com.georgeren.androidmiddle.scope.DaggerActivityComponent;
import com.georgeren.androidmiddle.scope.UserModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by georgeRen on 2017/8/29.
 */

public class InitApp extends Application{
    ActivityComponent activityComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        activityComponent = DaggerActivityComponent.builder().userModule(new UserModule()).build();
    }
    public ActivityComponent getActivityComponent(){
        return activityComponent;
    }
}
