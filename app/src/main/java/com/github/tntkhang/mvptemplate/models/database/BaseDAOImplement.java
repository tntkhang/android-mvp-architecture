package com.github.tntkhang.mvptemplate.models.database;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;

public class BaseDAOImplement<T extends RealmModel> implements BaseDAO<T> {

    private static final String TAG = BaseDAOImplement.class.getSimpleName();
    protected Realm realm;
    protected Class<T> entityClass;

    public BaseDAOImplement() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.realm = Realm.getDefaultInstance();
    }

    public BaseDAOImplement(Realm realm) {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.realm = realm;
    }

    @Override
    public boolean add(@NonNull final T entity) {
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(entity);
            realm.commitTransaction();
            return true;
        } catch (RealmException ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public void addList(@NonNull final List<T> entities) {
        addList(entities, null);
    }

    public void addList(@NonNull final List<T> entities, final DaoUpdateListener listener) {
        try {
            realm.executeTransactionAsync(realmExc -> {
                    realmExc.copyToRealmOrUpdate(entities);
            }, () -> {
                    if (listener != null) {
                        listener.onSuccess();
                    }
            }, error -> {
                    if (listener != null) {
                        listener.onError(error);
                        }
            });
        } catch (RealmException ex) {
            Log.e(TAG, ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    @Override
    public boolean addListSync(@NonNull final List<T> entities) {
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(entities);
            realm.commitTransaction();
            return true;
        } catch (RealmException ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public List<T> findAllSortByKey(String key, boolean isAscend) {
        RealmResults<T> entities = realm.where(entityClass)
                .findAll()
                .sort(key, isAscend ? Sort.ASCENDING : Sort.DESCENDING);

        return realm.copyFromRealm(entities);
    }


    @Override
    public T findById(@NonNull String fieldID, @NonNull String value) {
        T entity = realm.where(entityClass)
                .equalTo(fieldID, value)
                .findFirst();
        return entity;
    }

    @Override
    public List<T> findAll() {
        RealmResults<T> entities = realm.where(entityClass)
                .findAllAsync();

        return realm.copyFromRealm(entities);
    }

    @Override
    public void deleteAll() {
        realm.executeTransactionAsync(realmExc -> {
                RealmResults<T> entities = realmExc.where(entityClass)
                        .findAll();

                entities.deleteAllFromRealm();
        });
    }

    @Override
    public void delete(@NonNull final String fieldId, @NonNull final String value) {
        realm.executeTransactionAsync(realmExc -> {
                RealmObject entity = (RealmObject) findById(fieldId, value);
                if (entity != null) {
                    entity.deleteFromRealm();
                }
        });
    }

    @Override
    public void deleteAllSync() {
        realm.beginTransaction();
        RealmResults<T> entities = realm.where(entityClass)
                .findAll();

        entities.deleteAllFromRealm();
        realm.commitTransaction();
    }


    @Override
    public void close() {
        realm.close();
    }

    @Override
    public T findFirst() {
        T entity = realm.where(entityClass).findFirst();
        return entity;
    }

    public interface DaoUpdateListener {
        void onSuccess();

        void onError(Throwable error);
    }
}
