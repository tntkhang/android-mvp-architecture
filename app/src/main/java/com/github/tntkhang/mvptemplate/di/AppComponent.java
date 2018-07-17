package com.github.tntkhang.mvptemplate.di;

import com.github.tntkhang.mvptemplate.ui.home.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, DatabaseModule.class})
public interface AppComponent {
    void inject(HomeActivity homeActivity);
}
