package com.georgeren.androidmiddle.scope2.dagger.module;

import android.app.Activity;
import android.content.Context;


import com.georgeren.androidmiddle.scope2.annotation.BaseScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
@BaseScope
@Module
public class BaseModule {
	private Activity mActivity;
	private CompositeDisposable mDisposable = new CompositeDisposable();

	public BaseModule(Activity activity) {
		mActivity = activity;
	}

	/**
	 * 提供Activity,这里使用了ActivityScope域,则在此域中的mActivity对象只有一份(也就是局部单例)
	 * @return
	 */
	@BaseScope
	@Provides
    Activity providerActivity() {
		return mActivity;
	}

	@BaseScope
	@Provides
    Context providerContext() {
		return mActivity;
	}

	@BaseScope
	@Provides
    CompositeDisposable providerDisposable() {
		return mDisposable;
	}
}
