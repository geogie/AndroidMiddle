package com.georgeren.androidmiddle.scope;

import dagger.Component;

/**
 * Created by georgeRen on 2017/9/5.
 */
@Component(modules = UserModule2.class)
public interface ActivityComponent2 {
    void inject(TestActivity2 activity);
}
