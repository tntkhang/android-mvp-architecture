package com.github.tntkhang.mvptemplate.models.database.database.entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "post")
public class PostEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "userId")
    private int userId;

    public PostEntity(){

    }

    @Ignore
    public PostEntity(PostResponse response) {
        this.id = response.getId();
        this.title = response.getTitle();
        this.body = response.getBody();
        this.userId = response.getUserId();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
