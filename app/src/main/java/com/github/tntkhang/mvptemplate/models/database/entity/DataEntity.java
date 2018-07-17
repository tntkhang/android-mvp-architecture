package com.github.tntkhang.mvptemplate.models.database.entity;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class DataEntity implements RealmModel {

    @PrimaryKey
    private String image;
    private String link;

    public DataEntity() {
    }

    public DataEntity(String image, String link) {
        this.image = image;
        this.link = link;
    }

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
