package com.github.tntkhang.mvptemplate.ui.home;


import com.github.tntkhang.mvptemplate.models.network.DataResponse;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(DataResponse dataResponse);

}
