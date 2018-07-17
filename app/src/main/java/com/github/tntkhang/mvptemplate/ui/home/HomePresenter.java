package com.github.tntkhang.mvptemplate.ui.home;

import com.github.tntkhang.mvptemplate.models.database.dao.DataDAO;
import com.github.tntkhang.mvptemplate.ui.BasePresenter;
import com.github.tntkhang.mvptemplate.models.network.DataResponse;
import com.github.tntkhang.mvptemplate.models.database.entity.DataEntity;
import com.github.tntkhang.mvptemplate.networking.NetworkError;
import com.github.tntkhang.mvptemplate.networking.Service;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter {
    private final Service service;
    private final DataDAO dataDAO;
    private final HomeView view;

    public HomePresenter(Service service, DataDAO dataDAO, HomeView view) {
        super();
        this.service = service;
        this.dataDAO = dataDAO;
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
        DataEntity dataEntity = new DataEntity(dataResponse.getImage(), dataResponse.getLink());
        dataDAO.add(dataEntity);
    }
}
