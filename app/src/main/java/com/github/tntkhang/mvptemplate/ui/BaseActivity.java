package com.github.tntkhang.mvptemplate.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.tntkhang.mvptemplate.BaseApplication;
import com.github.tntkhang.mvptemplate.dagger.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectActivity(((BaseApplication) getApplication()).getAppComponent());
    }

    protected abstract void injectActivity(AppComponent appComponent);

}
