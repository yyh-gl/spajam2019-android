package com.isdl.spajam2019.Services;

import com.isdl.spajam2019.Models.QiitaItem;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * [@GET]，[@POST]等でリクエスト種別を変更
 *
 * @GET("エンドポイントURL") 返却はObservable
 * 参考　https://qiita.com/hymmr/items/cbb1013617cd43b8c7c4
 */

public interface ApiService {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @GET("y4m0jgy4")
    Observable<List<QiitaItem>> items();

    @POST("y4m0jgy4")
    Observable<Void> post(@Body HashMap<String, String> body);
    // 他に API があればここに並べる
}
