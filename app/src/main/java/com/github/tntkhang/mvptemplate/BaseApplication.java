package com.github.tntkhang.mvptemplate;

import android.app.Application;

import com.github.tntkhang.mvptemplate.di.AppComponent;
import com.github.tntkhang.mvptemplate.di.DaggerAppComponent;
import com.github.tntkhang.mvptemplate.networking.NetworkModule;

public class BaseApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(this)).build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
