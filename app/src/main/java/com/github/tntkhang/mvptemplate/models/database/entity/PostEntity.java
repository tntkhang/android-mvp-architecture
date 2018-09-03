package com.github.tntkhang.mvptemplate.models.database.entity;

import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class PostEntity  implements RealmModel {

    @PrimaryKey
    private int id;

    private String title;

    private String body;

    private int userId;

    public PostEntity() {
    }

    public PostEntity(PostResponse postResponse) {
        this.id = postResponse.getId();
        this.title = postResponse.getTitle();
        this.body = postResponse.getBody();
        this.userId = postResponse.getUserId();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
