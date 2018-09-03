package com.github.tntkhang.mvptemplate.models.network.responses;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("title")
    public String title;
    @SerializedName("detail")
    public String detail;
    @SerializedName("message")
    public String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public ErrorResponse() {
    }
}
