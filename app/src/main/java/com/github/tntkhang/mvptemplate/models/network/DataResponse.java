package com.github.tntkhang.mvptemplate.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class DataResponse {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("link")
    @Expose
    private String link;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}