package com.github.tntkhang.mvptemplate.networking;

import com.github.tntkhang.mvptemplate.models.DataResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Flowable<DataResponse> getCityList() {
        return networkService.getData();
    }
}
