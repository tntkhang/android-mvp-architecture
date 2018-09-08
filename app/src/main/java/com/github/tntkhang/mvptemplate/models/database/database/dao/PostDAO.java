package com.github.tntkhang.mvptemplate.models.database.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.tntkhang.mvptemplate.models.database.database.entitiy.PostEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PostDAO {

    @Query("SELECT * FROM post ORDER BY uid DESC")
    Flowable<List<PostEntity>> getAll();

    @Insert
    void insert(PostEntity callDetailEntity);

    @Update
    void update(PostEntity callDetailEntity);

    @Delete
    void delete(PostEntity callDetailEntity);
}
