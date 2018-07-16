package com.github.tntkhang.mvptemplate.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.github.tntkhang.mvptemplate.ui.BaseActivity;
import com.github.tntkhang.mvptemplate.R;
import com.github.tntkhang.mvptemplate.models.network.DataResponse;
import com.github.tntkhang.mvptemplate.networking.Service;
import com.github.tntkhang.mvptemplate.utils.Connectivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView {
    @Inject
    public Service service;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.list)
    RecyclerView list;

    private HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        renderView();
        init();

        presenter = new HomePresenter(service, this);
        if (Connectivity.isConnected(this)) {
            presenter.getCityList();
        }

    }

    public void renderView(){
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(DataResponse dataResponse) {
        List<DataResponse> listData = new ArrayList<>();
        listData.add(dataResponse);
        listData.add(dataResponse);
        listData.add(dataResponse);

        HomeAdapter adapter = new HomeAdapter(getApplicationContext(), listData, item -> {
                // Item click action
                });

        list.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
