package com.github.tntkhang.mvptemplate.dagger;

import android.content.Context;

import com.github.tntkhang.mvptemplate.BuildConfig;
import com.github.tntkhang.mvptemplate.R;
import com.github.tntkhang.mvptemplate.networking.APIServiceHolder;
import com.github.tntkhang.mvptemplate.networking.TokenAuthenticator;
import com.github.tntkhang.mvptemplate.networking.TokenInterceptor;
import com.github.tntkhang.mvptemplate.networking.NetworkService;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        File cacheFile = new File(context.getCacheDir(), context.getString(R.string.app_name));
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }


    @Provides
    @Singleton
    Dispatcher provideDispatcher() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        return dispatcher;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, Context context, TokenInterceptor chain, TokenAuthenticator authenticator, Dispatcher dispatcher) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain)
                .addInterceptor(new ChuckInterceptor(context))
                .authenticator(authenticator)
                .cache(cache)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    public NetworkService providesNetworkService(
            Retrofit retrofit, APIServiceHolder apiServiceHolder){
        NetworkService iNetworkService = retrofit.create(NetworkService.class);
        apiServiceHolder.setAPIService(iNetworkService);
        return retrofit.create(NetworkService.class);
    }

}
