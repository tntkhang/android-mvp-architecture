package com.github.tntkhang.mvptemplate.ui.home;

import com.github.tntkhang.mvptemplate.ui.BasePresenter;
import com.github.tntkhang.mvptemplate.models.network.DataResponse;
import com.github.tntkhang.mvptemplate.models.entitiy.DataEntity;
import com.github.tntkhang.mvptemplate.networking.NetworkError;
import com.github.tntkhang.mvptemplate.networking.Service;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class HomePresenter extends BasePresenter {
    private final Service service;
    private final HomeView view;

    public HomePresenter(Service service, HomeView view) {
        super();
        this.service = service;
        this.view = view;
    }

    public void getCityList() {
        view.showWait();
        Disposable disposable = service.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleOnSuccess, this::handleOnError, this::handleOnComplete);
        disposeBag.add(disposable);
    }

    private void handleOnSuccess(DataResponse dataResponse) {
        view.removeWait();
        view.getCityListSuccess(dataResponse);

        saveToDatabase(dataResponse);
    }

    private void handleOnError(Throwable throwable) {
        view.onFailure(((NetworkError) throwable).getAppErrorMessage());
    }

    private void handleOnComplete() {
        view.removeWait();
    }

    private void saveToDatabase(DataResponse dataResponse) {
        Realm realm = Realm.getDefaultInstance();

        DataEntity dataEntity = new DataEntity(dataResponse.getImage(), dataResponse.getLink());
        realm.executeTransaction(rlm -> rlm.copyToRealmOrUpdate(dataEntity));
    }
}
