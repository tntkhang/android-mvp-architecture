package com.github.tntkhang.mvptemplate.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.tntkhang.mvptemplate.R;
import com.github.tntkhang.mvptemplate.models.network.responses.PostResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<PostResponse> data;

    public HomeAdapter(List<PostResponse> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }


    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);

        holder.mItem = data.get(position);

        holder.tvTitle.setText(holder.mItem.getId() + ". " + holder.mItem.getTitle());
        holder.tvDescription.setText(holder.mItem.getBody());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(PostResponse Item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;

        PostResponse mItem;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        void click(final PostResponse dataResponse, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onClick(dataResponse));
        }
    }


}
