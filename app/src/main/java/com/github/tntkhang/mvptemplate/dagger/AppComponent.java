package com.github.tntkhang.mvptemplate.dagger;

import android.content.Context;

import com.github.tntkhang.mvptemplate.models.database.dao.PostDAO;
import com.github.tntkhang.mvptemplate.networking.Service;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {
    Context provideContext();
    Service provideService();
    PostDAO providePostDAO();
}
