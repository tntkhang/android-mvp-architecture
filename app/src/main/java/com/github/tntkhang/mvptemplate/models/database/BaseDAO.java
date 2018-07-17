package com.github.tntkhang.mvptemplate.models.database;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.RealmModel;

public interface BaseDAO<T extends RealmModel> {

    boolean add(T entity);

    void addList(List<T> entities);

    T findById(String fieldID, String value);

    List<T> findAll();

    void deleteAll();

    void delete(String fieldId, String value);

    void deleteAllSync();

    void close();

    T findFirst();

    boolean addListSync(@NonNull final List<T> entities);

    List<T> findAllSortByKey(String key, boolean isAscend);
}