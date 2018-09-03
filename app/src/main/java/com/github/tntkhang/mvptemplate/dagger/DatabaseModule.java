package com.github.tntkhang.mvptemplate.dagger;

import com.github.tntkhang.mvptemplate.models.database.dao.PostDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    PostDAO postDAO;

    public DatabaseModule() {
        postDAO = new PostDAO();
    }

    @Provides
    @Singleton
    PostDAO providePostDAO() {
        return postDAO;
    }
}
