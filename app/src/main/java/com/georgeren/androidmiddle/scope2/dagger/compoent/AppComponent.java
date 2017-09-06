package com.georgeren.androidmiddle.scope2.dagger.compoent;

import com.georgeren.androidmiddle.scope2.dagger.module.AppModule;
import com.georgeren.androidmiddle.scope2.dagger.module.BaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
//这里使用单例域,目标类Application
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
	//这里暴露子组件
	BaseComponent baseComponent(BaseModule baseModule);
}
