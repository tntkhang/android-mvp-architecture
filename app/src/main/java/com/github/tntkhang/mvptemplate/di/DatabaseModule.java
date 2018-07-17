package com.github.tntkhang.mvptemplate.di;

import com.github.tntkhang.mvptemplate.models.database.dao.DataDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    DataDAO dataDAO;

    public DatabaseModule() {
        dataDAO = new DataDAO();
    }

    @Provides
    @Singleton
    DataDAO provideDataDAO() {
        return dataDAO;
    }
}
