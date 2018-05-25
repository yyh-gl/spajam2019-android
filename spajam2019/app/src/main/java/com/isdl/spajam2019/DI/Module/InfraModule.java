package com.isdl.spajam2019.DI.Module;

import com.isdl.spajam2019.Services.QiitaApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by takayayuuki on 2018/05/25.
 */

@Module
public class InfraModule {


    @Singleton
    @Provides
    public QiitaApiService provideQiitaApiService(Retrofit retrofit) {
        return retrofit.create(QiitaApiService.class);
    }
}
