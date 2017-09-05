package com.georgeren.androidmiddle.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Created by georgeRen on 2017/9/5.
 * Scope 自定义注解，局部单例，共享。
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
