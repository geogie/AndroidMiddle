package com.georgeren.rxbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by georgeRen on 2017/8/29.
 */

public class MainActivity extends AppCompatActivity {
    private Observable<Boolean> observable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d("发送：true");
                RxBus.getInstance().post(true);
            }
        });

        observable = RxBus.getInstance().register(Boolean.class);
        observable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isNightMode) throws Exception {
                Logger.d("接收-isNightMode："+isNightMode);
                if (isNightMode) {
                    startActivity(new Intent(MainActivity.this, TestActivity.class));
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        RxBus.getInstance().unregister(Boolean.class, observable);
        super.onDestroy();
    }
}
