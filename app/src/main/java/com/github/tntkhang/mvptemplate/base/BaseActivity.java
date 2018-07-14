package com.github.tntkhang.mvptemplate.base;

import android.support.v7.app.AppCompatActivity;

import com.github.tntkhang.mvptemplate.BaseApplication;
import com.github.tntkhang.mvptemplate.di.AppComponent;

public class BaseActivity extends AppCompatActivity {

    public AppComponent getComponent() {
        return ((BaseApplication) getApplication()).getAppComponent();
    }
}
