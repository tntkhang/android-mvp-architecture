package com.github.tntkhang.mvptemplate.home;


import com.github.tntkhang.mvptemplate.models.DataResponse;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(DataResponse dataResponse);

}
