package com.georgeren.androidmiddle.scope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by georgeRen on 2017/9/5.
 */
@Module
public class UserModule2 {
    @Provides
    User providesUser(){
        return new User();
    }
}
