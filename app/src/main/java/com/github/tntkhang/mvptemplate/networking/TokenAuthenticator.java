package com.github.tntkhang.mvptemplate.networking;

import android.support.annotation.NonNull;

import com.github.tntkhang.mvptemplate.models.network.responses.AuthenResponse;
import com.github.tntkhang.mvptemplate.utils.Constants;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import khangtran.preferenceshelper.PreferencesHelper;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

@Singleton
public class TokenAuthenticator implements Authenticator {
    private APIServiceHolder apiService;

    @Inject
    public TokenAuthenticator(@NonNull APIServiceHolder apiService) {
        this.apiService = apiService;
    }

    @Override
    public Request authenticate(Route route, Response response) {
        if(responseCount(response) >= 2) {
            return null;
        }

        HashMap<String, String> loginParams = new HashMap<>();
        loginParams.put("username", "admin");
        loginParams.put("password", "admin");

        // We need a new client, since we don't want to make another call using our client with access token
        Call<AuthenResponse> responseCall = apiService.apiService().refreshToken(loginParams);
        try {
            retrofit2.Response<AuthenResponse> tokenResponse = responseCall.execute();
            if(tokenResponse.code() == 200 && tokenResponse.body() != null) {
                String newToken = tokenResponse.body().getToken();
                PreferencesHelper.getInstance().setValue(Constants.Prefs.TOKEN, newToken);

                return response.request().newBuilder()
                        .header("Authorization", "Bearer " + newToken)
                        .build();
            } else {
                return null;
            }
        } catch(IOException e) {
            return null;
        }
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
