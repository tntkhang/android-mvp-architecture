package com.github.tntkhang.mvptemplate.networking;

import android.support.annotation.Nullable;

import com.github.tntkhang.mvptemplate.networking.NetworkService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class APIServiceHolder {

    private NetworkService apiService;

    @Inject
    public APIServiceHolder() {
    }

    @Nullable
    NetworkService apiService() {
        return apiService;
    }

    public void setAPIService(NetworkService apiService) {
        this.apiService = apiService;
    }
}
