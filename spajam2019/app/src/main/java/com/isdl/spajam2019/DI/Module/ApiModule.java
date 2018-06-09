package com.isdl.spajam2019.DI.Module;

import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by takayayuuki on 2018/05/25.
 */

@Module
public class ApiModule {


    @Singleton
    @Provides
    public ApiService provideQiitaApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
