package com.github.tntkhang.mvptemplate.models.database.dao;

import com.github.tntkhang.mvptemplate.models.database.BaseDAOImplement;
import com.github.tntkhang.mvptemplate.models.database.entity.PostEntity;

import javax.inject.Inject;

public class PostDAO extends BaseDAOImplement<PostEntity> {

    @Inject
    public PostDAO() {
    }
}
