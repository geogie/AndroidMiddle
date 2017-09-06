package com.georgeren.androidmiddle.scope2.dagger.module;

import com.georgeren.androidmiddle.InitApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
@Singleton
@Module
public class AppModule {
	private InitApp mApp;

	/**
	 * 提供构造
	 * @param app
	 */
	public AppModule(InitApp app) {
		mApp = app;
	}

	/**
	 * 将App提供出去
	 * @return
	 */
	@Singleton
	@Provides
	InitApp providerApp() {
		return mApp;
	}
}
