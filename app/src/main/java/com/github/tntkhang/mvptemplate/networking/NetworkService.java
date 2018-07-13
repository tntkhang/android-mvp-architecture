package com.github.tntkhang.mvptemplate.networking;


import com.github.tntkhang.mvptemplate.models.DataResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {

    @GET("floof/")
    Observable<DataResponse> getData();

}
