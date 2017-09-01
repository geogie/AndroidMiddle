package com.georgeren.retrofit.module;

import com.georgeren.retrofit.IApi;
import com.georgeren.retrofit.InitApp;
import com.georgeren.retrofit.R;
import com.georgeren.retrofit.RetrofitFactory;
import com.georgeren.retrofit.bean.ZhuanlanBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by georgeRen on 2017/8/30.
 */

public class MainPresenter implements IMain.Presenter {
    private IMain.View view;
    private Call<ZhuanlanBean> call;

    public MainPresenter(IMain.View view) {
        this.view = view;
    }

    @Override
    public void doLoading() {
        view.onShowLoading();

        Observable
                .create(new ObservableOnSubscribe<List<ZhuanlanBean>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<ZhuanlanBean>> e) throws Exception {
//                        e.onNext(dao.query(type));
                        e.onNext(new ArrayList<ZhuanlanBean>());
                    }
                })
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<ZhuanlanBean>, Observable<List<ZhuanlanBean>>>() {
                    @Override
                    public Observable<List<ZhuanlanBean>> apply(@NonNull List<ZhuanlanBean> list) throws Exception {
                        if (null != list && list.size() > 0) {
                            return Observable.just(list);
                        } else {
                            list = retrofitRequest();
                            return Observable.just(list);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(view.<List<ZhuanlanBean>>bindToLife())
                .subscribe(new Consumer<List<ZhuanlanBean>>() {
                    @Override
                    public void accept(@NonNull List<ZhuanlanBean> list) throws Exception {
                        if (null != list && list.size() > 0) {
                            doSetAdapter(list);
                        } else {
                            doShowFail();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        doShowFail();
                    }
                });
    }

    @Override
    public void doSetAdapter(List<ZhuanlanBean> list) {
        view.onSetAdapter(list);
        view.onHideLoading();
    }

    @Override
    public void doShowFail() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doRefresh() {
        view.onShowLoading();
        view.onRequestData();
    }

    @Override
    public void onDestroy() {
        if (call != null && call.isCanceled()) {
            call.cancel();
        }
    }

    private List<ZhuanlanBean> retrofitRequest() {

//        switch (type) {
//            case TYPE_PRODUCT:
//                ids = InitApp.AppContext.getResources().getStringArray(R.array.product);
//                break;
//            case TYPE_MUSIC:
//                ids = InitApp.AppContext.getResources().getStringArray(R.array.music);
//                break;
//            case TYPE_LIFE:
//                ids = InitApp.AppContext.getResources().getStringArray(R.array.life);
//                break;
//            case TYPE_EMOTION:
//                ids = InitApp.AppContext.getResources().getStringArray(R.array.emotion);
//                break;
//            case TYPE_FINANCE:
//                ids = InitApp.AppContext.getResources().getStringArray(R.array.profession);
//                break;
//            case TYPE_ZHIHU:
//                ids = InitApp.AppContext.getResources().getStringArray(R.array.zhihu);
//                break;
//        }
        String[] ids = InitApp.AppContext.getResources().getStringArray(R.array.product);


        final List<ZhuanlanBean> list = new ArrayList<>();

        IApi IApi = RetrofitFactory.getRetrofit().create(IApi.class);
        for (String id : ids) {
            call = IApi.getZhuanlanBean(id);
            try {
                Response<ZhuanlanBean> response = call.execute();
                if (response.isSuccessful()) {
                    list.add(response.body());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        for (ZhuanlanBean bean : list) {
//            dao.add(type, bean);
//        }

        return list;
    }
}
