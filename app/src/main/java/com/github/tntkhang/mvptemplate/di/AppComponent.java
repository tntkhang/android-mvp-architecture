package com.github.tntkhang.mvptemplate.di;

import com.github.tntkhang.mvptemplate.home.HomeActivity;
import com.github.tntkhang.mvptemplate.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface AppComponent {
    void inject(HomeActivity homeActivity);
}
