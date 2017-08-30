package com.georgeren.drawer;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by georgeRen on 2017/8/29.
 */

public class InitApp extends Application{
    public static Context AppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
