package com.isdl.spajam2019.Services;

import com.isdl.spajam2019.Models.CrossMusic;
import com.isdl.spajam2019.Models.Live;
import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Models.UserCrossMusic;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * [@GET]，[@POST]等でリクエスト種別を変更
 * <p>
 * エンドポイントURL:http://localhost:3000/api/
 * <p>
 * 参考　https://qiita.com/hymmr/items/cbb1013617cd43b8c7c4
 */
public interface ApiService {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })

    @GET("users")
    Single<List<User>> getUser();

    @GET("users/{id}")
    Single<User> getUserInfo(@Path("id") int userId);

    @GET("musics/possessed/user/{id}")
    Single<List<Music>> getPossessedMusic(@Path("id") int userId);

    @GET("musics/cross/{id}")
    Single<List<CrossMusic>> getCrossMusic(@Path("id") int userId);

    @GET("musics/uploaded/user/{id}")
    Single<List<Music>> getUploadedMusic(@Path("id") int userId);

    @GET("lives/{live_id}")
    Single<Live> getLiveInfo(@Path("live_id") int liveId);

    @POST("musics/cross")
    Single<List<UserCrossMusic>> postCrossMusic(@Query("sender_id") int senderId, @Query("reciever_id") int recieverId);

    @POST("lives/like/{live_id}")
    Single<Live> postLike(@Path("live_id") int liveId);

    @POST("lives/switch/{live_id}")
    Single<Boolean> switchLiveStatus(@Path("live_id") int liveId);

    @POST("users")
    Completable postUser(@Body User user);
    // 他に API があればここに並べる

    @POST("musics/cross/user")
    Completable acceptCrossMusic(@Query("user_id") int userId,@Query("user_cross_music_id") int userCrossMusicId);

    @DELETE("musics/cross")
    Completable deleteCrossMusic(@Query("user_cross_music_id") int userCrossMusicId);

}
