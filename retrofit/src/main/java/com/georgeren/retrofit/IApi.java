package com.georgeren.retrofit;

import com.georgeren.retrofit.bean.ZhuanlanBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by georgeRen on 2017/8/30.
 */

public interface IApi {
    String API_BASE = "https://zhuanlan.zhihu.com/";

    /**
     * 获取专栏信息
     * https://zhuanlan.zhihu.com/api/columns/design
     *
     * @param slug 专栏ID
     * @return
     */
    @GET("api/columns/{slug}")
    Call<ZhuanlanBean> getZhuanlanBean(@Path("slug") String slug);
}
