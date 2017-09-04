package com.georgeren.savepic2local;

import android.app.Application;
import android.content.Context;

/**
 * Created by georgeRen on 2017/9/4.
 */

public class InitApp extends Application{
    public static Context AppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
    }
}
