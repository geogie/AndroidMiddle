package com.georgeren.androidmiddle.scope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by georgeRen on 2017/9/5.
 */
@Module
public class UserModule {
    @Provides
    @UserScope
    User providesUser() {
        return new User();
    }
}
