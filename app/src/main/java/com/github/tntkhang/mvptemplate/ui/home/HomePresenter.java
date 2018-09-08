package com.github.tntkhang.mvptemplate.ui.home;

import android.content.Context;

import com.github.tntkhang.mvptemplate.models.database.database.entitiy.PostEntity;
import com.github.tntkhang.mvptemplate.models.database.database.repository.PostRepository;
import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;
import com.github.tntkhang.mvptemplate.networking.GeneralError;
import com.github.tntkhang.mvptemplate.networking.Service;
import com.github.tntkhang.mvptemplate.ui.BasePresenter;
import com.github.tntkhang.mvptemplate.utils.Toaster;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter {
    private final Service service;
    private final PostRepository postRepository;
    private final HomeView view;
    private final Context context;

    @Inject
    public HomePresenter(Context context, Service service, PostRepository postRepository, HomeView view) {
        super();
        this.context = context;
        this.service = service;
        this.postRepository = postRepository;
        this.view = view;
    }

    public void getCityList() {
        view.showWait();
        Disposable disposable = service.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleOnSuccess, this::handleOnError, this::handleOnComplete);
        disposeBag.add(disposable);
    }

    private void handleOnSuccess(List<PostResponse> postResponses) {
        view.removeWait();
        view.getCityListSuccess(postResponses);

        if (postResponses.size() == 0) return;
        saveToDatabase(postResponses.get(0));
    }

    private void handleOnError(Throwable throwable) {
        String errorMessage = GeneralError.getAppErrorMessage(throwable);
        Toaster.error(context, errorMessage);
        view.removeWait();
    }

    private void handleOnComplete() {
        view.removeWait();
    }

    private void saveToDatabase(PostResponse postResponse) {
        PostEntity postEntity = new PostEntity(postResponse);
        postRepository.insert(postEntity);
        Toaster.success(context, "Save post to db success");
    }
}
