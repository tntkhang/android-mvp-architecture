package com.github.tntkhang.mvptemplate;

import android.app.Application;

import com.github.tntkhang.mvptemplate.di.AppComponent;
import com.github.tntkhang.mvptemplate.di.DaggerAppComponent;
import com.github.tntkhang.mvptemplate.di.DatabaseModule;
import com.github.tntkhang.mvptemplate.di.NetworkModule;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class BaseApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());

            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("mvptemplate.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

        initializeDependencies();
    }

    private void initializeDependencies() {
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(this))
                .databaseModule(new DatabaseModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
