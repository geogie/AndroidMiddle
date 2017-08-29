package com.georgeren.androidmiddle;

import com.georgeren.androidmiddle.base.IBasePresenter;
import com.georgeren.androidmiddle.base.IBaseView;

/**
 * Created by georgeRen on 2017/8/29.
 */

public interface IMain {
    interface View extends IBaseView{
        void onShowLoading();
    }
    interface Presenter extends IBasePresenter{
        void doLoading();
    }
}
