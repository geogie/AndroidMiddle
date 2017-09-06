package com.georgeren.androidmiddle.scope2.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by georgeRen on 2017/9/6.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseScope {
}
