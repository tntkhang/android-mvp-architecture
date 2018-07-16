package com.github.tntkhang.mvptemplate.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.tntkhang.mvptemplate.R;
import com.github.tntkhang.mvptemplate.models.network.DataResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<DataResponse> data;
    private Context context;

    public HomeAdapter(Context context, List<DataResponse> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
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
        holder.textView.setText(data.get(position).getLink());

        String images = data.get(position).getImage();

        Glide.with(context)
                .load(images)
                .into(holder.background);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(DataResponse Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.image)
        ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void click(final DataResponse dataResponse, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(dataResponse);
                }
            });
        }
    }


}
