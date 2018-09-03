package com.github.tntkhang.mvptemplate;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.github.tntkhang.mvptemplate.dagger.AppComponent;
import com.github.tntkhang.mvptemplate.dagger.AppModule;
import com.github.tntkhang.mvptemplate.dagger.DaggerAppComponent;
import com.github.tntkhang.mvptemplate.dagger.DatabaseModule;
import com.github.tntkhang.mvptemplate.dagger.NetworkModule;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import khangtran.preferenceshelper.PreferencesHelper;

public class BaseApplication extends MultiDexApplication {
    private static BaseApplication INSTANCE;
    AppComponent appComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("mvptemplate.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

        initializeDependencies();

        PreferencesHelper.initHelper(this);
    }

    private void initializeDependencies() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .networkModule(new NetworkModule())
                .databaseModule(new DatabaseModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static BaseApplication getApplication() {
        return INSTANCE;
    }
}
