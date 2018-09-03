package com.github.tntkhang.mvptemplate.networking;

import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class Service {
    private final NetworkService networkService;

    @Inject
    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Flowable<List<PostResponse>> getPosts() {
        return networkService.getPosts();
    }
}
