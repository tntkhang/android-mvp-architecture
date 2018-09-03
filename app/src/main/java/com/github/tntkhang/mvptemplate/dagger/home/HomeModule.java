package com.github.tntkhang.mvptemplate.dagger.home;


import com.github.tntkhang.mvptemplate.ui.home.HomeView;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
    private final HomeView homeView;

    public HomeModule(HomeView homeView) {
        this.homeView = homeView;
    }

    @Provides
    @HomeScope
    public HomeView provideHomeView() {
        return homeView;
    }

}
