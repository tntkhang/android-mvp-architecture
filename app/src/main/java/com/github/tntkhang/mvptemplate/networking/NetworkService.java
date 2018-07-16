package com.github.tntkhang.mvptemplate.networking;


import com.github.tntkhang.mvptemplate.models.network.DataResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("floof/")
    Flowable<DataResponse> getData();
}
