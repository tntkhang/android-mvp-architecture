package com.github.tntkhang.mvptemplate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.github.tntkhang.mvptemplate.di.AppComponent;

public class BaseActivity extends AppCompatActivity {

    public AppComponent getComponent() {
        return ((BaseApplication) getApplication()).getAppComponent();
    }
}
