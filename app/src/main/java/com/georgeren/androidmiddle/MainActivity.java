package com.georgeren.androidmiddle;

import android.os.Bundle;
import android.view.View;

import com.georgeren.androidmiddle.base.BaseActivity;
import com.georgeren.androidmiddle.injector.component.DaggerMainComponent;
import com.georgeren.androidmiddle.injector.module.MainModule;
import com.orhanobut.logger.Logger;

/**
 * dagger2 + MVP
 */
public class MainActivity extends BaseActivity<IMain.Presenter> implements IMain.View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.doLoading();
            }
        });
    }

    @Override
    public void onShowLoading() {
        Logger.d("onShowLoading-");
    }

    @Override
    protected void initInjector() {
        DaggerMainComponent
                .builder()
                .mainModule(new MainModule(this))
                .build().inject(this);
    }
}
