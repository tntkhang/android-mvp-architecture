package com.github.tntkhang.mvptemplate.dagger.home;

import com.github.tntkhang.mvptemplate.dagger.AppComponent;
import com.github.tntkhang.mvptemplate.ui.home.HomeActivity;

import dagger.Component;

@HomeScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void injectHome(HomeActivity homeActivity);
}
