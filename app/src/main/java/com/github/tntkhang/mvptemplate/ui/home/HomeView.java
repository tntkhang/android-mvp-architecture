package com.github.tntkhang.mvptemplate.ui.home;


import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;

import java.util.List;

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(List<PostResponse> postResponses);

}
