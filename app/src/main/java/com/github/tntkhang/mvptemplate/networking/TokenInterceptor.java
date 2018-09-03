package com.github.tntkhang.mvptemplate.networking;

import com.github.tntkhang.mvptemplate.utils.Constants;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import khangtran.preferenceshelper.PreferencesHelper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class TokenInterceptor implements Interceptor {

    @Inject
    public TokenInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder1 = original.newBuilder();
        String idToken = PreferencesHelper.getInstance().getStringValue(Constants.Prefs.TOKEN, "");
        if (!idToken.equalsIgnoreCase("")) {
            builder1.addHeader("Authorization", "Bearer " + idToken);
        }

        builder1.addHeader("Content-Type", "application/json");
        builder1.method(original.method(), original.body());
        return chain.proceed(builder1.build());
    }
}
