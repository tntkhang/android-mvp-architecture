package com.github.tntkhang.mvptemplate.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter {

    protected CompositeDisposable disposeBag;

    public BasePresenter() {
        disposeBag = new CompositeDisposable();
    }

    public void onStop() {
        disposeBag.clear();
    }

}
