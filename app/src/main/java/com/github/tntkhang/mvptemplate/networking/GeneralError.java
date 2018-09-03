package com.github.tntkhang.mvptemplate.networking;

import android.text.TextUtils;

import com.github.tntkhang.mvptemplate.models.network.responses.ErrorResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.Map;

import retrofit2.HttpException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class GeneralError extends Throwable {
    public static final String DEFAULT_ERROR_MESSAGE = "Lỗi hệ thống. Vui lòng thử lại sau !";
    public static final String NETWORK_ERROR_MESSAGE = "Không có kết nối mạng";
    private static final String ERROR_MESSAGE_HEADER = "Error-Message";
    private static final String WRONG_USERNAME_PASSWORD = "Sai tên đăng nhập hoặc mật khẩu.";
    private final Throwable error;

    public GeneralError(Throwable e) {
        super(e);
        this.error = e;
    }

    public String getMessage() {
        return error.getMessage();
    }

    public static boolean isAuthFailure(Throwable throwable) {
        return throwable instanceof HttpException &&
                ((HttpException) throwable).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    public static String getAppErrorMessage(Throwable throwable) {
        if (throwable instanceof ConnectException || throwable instanceof  IOException) return NETWORK_ERROR_MESSAGE;
        if (!(throwable instanceof HttpException)) return DEFAULT_ERROR_MESSAGE;
        retrofit2.Response<?> response = ((HttpException) throwable).response();
        if (response != null && response.code() != 500) {
            if (isAuthFailure(throwable)) return WRONG_USERNAME_PASSWORD;
            String status = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) return status;

            Map<String, List<String>> headers = response.headers().toMultimap();
            if (headers.containsKey(ERROR_MESSAGE_HEADER))
                return headers.get(ERROR_MESSAGE_HEADER).get(0);
        }

        return DEFAULT_ERROR_MESSAGE;
    }

    protected static String getJsonStringFromResponse(final retrofit2.Response<?> response) {
        try {
            String jsonString = response.errorBody().string();
            ErrorResponse errorErrorResponse = new Gson().fromJson(jsonString, ErrorResponse.class);
            return errorErrorResponse.status;
        } catch (Exception e) {
            return null;
        }
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneralError that = (GeneralError) o;

        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
