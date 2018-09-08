package com.github.tntkhang.mvptemplate.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.github.tntkhang.mvptemplate.R;
import com.github.tntkhang.mvptemplate.models.database.database.AppDatabase;
import com.github.tntkhang.mvptemplate.models.database.database.dao.PostDAO;
import com.github.tntkhang.mvptemplate.models.database.database.repository.PostRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public Executor getExecutor(){
        return  Executors.newFixedThreadPool(2);
    }

    @Provides
    @Singleton
    public AppDatabase getAppDatabase(Context context){
        return  Room.databaseBuilder(context,
                AppDatabase.class,
                context.getString(R.string.app_name)).build();
    }

    @Provides
    @Singleton
    public PostDAO providePostDAO(AppDatabase appDatabase){
        return appDatabase.postDao();
    }

    @Provides
    @Singleton
    public PostRepository providePostRepository(PostDAO notificationDAO, Executor exec){
        return new PostRepository(notificationDAO, exec);
    }
}
