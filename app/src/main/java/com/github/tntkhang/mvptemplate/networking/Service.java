package com.github.tntkhang.mvptemplate.networking;


import com.github.tntkhang.mvptemplate.models.DataResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCityListCallback callback) {

        return networkService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends DataResponse>>() {
                    @Override
                    public Observable<? extends DataResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<DataResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(DataResponse dataResponse) {
                        callback.onSuccess(dataResponse);

                    }
                });
    }

    public interface GetCityListCallback{
        void onSuccess(DataResponse dataResponse);

        void onError(NetworkError networkError);
    }
}
