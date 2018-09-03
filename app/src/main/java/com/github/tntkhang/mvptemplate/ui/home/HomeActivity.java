package com.github.tntkhang.mvptemplate.ui.home;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.tntkhang.mvptemplate.dagger.AppComponent;
import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;
import com.github.tntkhang.mvptemplate.ui.BaseActivity;
import com.github.tntkhang.mvptemplate.R;
import com.github.tntkhang.mvptemplate.dagger.home.DaggerHomeComponent;
import com.github.tntkhang.mvptemplate.dagger.home.HomeComponent;
import com.github.tntkhang.mvptemplate.dagger.home.HomeModule;
import com.github.tntkhang.mvptemplate.utils.Connectivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeView {
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.rv_item)
    RecyclerView rvItem;

    @Inject
    HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderView();
        init();

        if (Connectivity.isConnected(this)) {
            presenter.getCityList();
        }

    }

    @Override
    protected void injectActivity(AppComponent appComponent) {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this))
                .appComponent(appComponent)
                .build();
        homeComponent.injectHome(this);
    }

    public void renderView() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    public void init() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvItem.setLayoutManager(layoutManager);
        rvItem.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvItem.setItemAnimator(new DefaultItemAnimator());
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
    public void getCityListSuccess(List<PostResponse> postResponses) {

        HomeAdapter adapter = new HomeAdapter(postResponses, item -> {
            Toast.makeText(HomeActivity.this, "Item id: " + item.getId(), Toast.LENGTH_LONG).show();
        });

        rvItem.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
