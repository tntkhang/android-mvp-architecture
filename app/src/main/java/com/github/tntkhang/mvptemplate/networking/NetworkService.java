package com.github.tntkhang.mvptemplate.networking;


import com.github.tntkhang.mvptemplate.models.network.responses.AuthenResponse;
import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {

    @POST("authenticate")
    Observable<AuthenResponse> authenticate(@Body HashMap<String, String> arguments);

    @POST("refreshToken")
    Call<AuthenResponse> refreshToken(@Body HashMap<String, String> arguments);

    @GET("posts")
    Flowable<List<PostResponse>> getPosts();
}
