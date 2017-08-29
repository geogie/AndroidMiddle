package com.georgeren.androidmiddle;

import com.orhanobut.logger.Logger;

/**
 * Created by georgeRen on 2017/8/29.
 */

public class MainPresenter implements IMain.Presenter{
    private IMain.View view;
    public MainPresenter(IMain.View view){
        this.view = view;
    }
    @Override
    public void doLoading() {
        Logger.d("doLoading-");
        view.onShowLoading();
    }
}
