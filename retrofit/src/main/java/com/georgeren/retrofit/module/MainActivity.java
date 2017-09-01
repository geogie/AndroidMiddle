package com.georgeren.retrofit.module;

import android.os.Bundle;
import android.view.View;

import com.georgeren.retrofit.R;
import com.georgeren.retrofit.base.BaseActivity;
import com.georgeren.retrofit.bean.ZhuanlanBean;
import com.georgeren.retrofit.injector.component.DaggerMainComponent;
import com.georgeren.retrofit.injector.module.MainModule;

import java.util.List;

public class MainActivity extends BaseActivity<IMain.Presenter> implements IMain.View {
    @Override
    protected void initInjector() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRequestData();
            }
        });
    }

    @Override
    public void onStop() {
        presenter.onDestroy();
        super.onStop();
    }

    @Override
    public void onRequestData() {
        presenter.doLoading();
    }

    @Override
    public void onSetAdapter(List<ZhuanlanBean> list) {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

}
