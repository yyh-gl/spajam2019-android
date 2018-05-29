package com.isdl.spajam2019.Services;

import com.isdl.spajam2019.Models.QiitaItem;
import com.isdl.spajam2019.Models.User;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * [@GET]，[@POST]等でリクエスト種別を変更
 * <p>
 * エンドポイントURL:http://119.228.76.176:62455/api/
 * <p>
 * 参考　https://qiita.com/hymmr/items/cbb1013617cd43b8c7c4
 */
//http://119.228.76.176:62455/api/users
public interface ApiService {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @GET("1avwb2j1")
    Single<List<QiitaItem>> items();

    @GET("users")
    Single<List<User>> getUser();

    @POST("1avwb2j1")
    Completable post(@Body HashMap<String, String> body);
    // 他に API があればここに並べる
}
