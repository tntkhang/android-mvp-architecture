package com.github.tntkhang.mvptemplate.models.database.database.repository;

import com.github.tntkhang.mvptemplate.models.database.database.dao.PostDAO;
import com.github.tntkhang.mvptemplate.models.database.database.entitiy.PostEntity;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Flowable;

public class PostRepository {

    private PostDAO callDetailDAO;
    private Executor executor;

    public PostRepository(PostDAO callDetailDAO, Executor exec) {
        this.callDetailDAO = callDetailDAO;
        executor = exec;
    }

    public Flowable<List<PostEntity>> getAll() {
        return callDetailDAO.getAll();
    }

    public void insert(PostEntity callDetailEntity) {
        executor.execute(() -> callDetailDAO.insert(callDetailEntity));
    }

    public void update(PostEntity callDetailEntity) {
        executor.execute(() -> callDetailDAO.update(callDetailEntity));
    }

    public void delete(PostEntity callDetailEntity) {
        executor.execute(() -> callDetailDAO.delete(callDetailEntity));
    }
}
