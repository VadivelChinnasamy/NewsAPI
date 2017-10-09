package com.newssource.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.newssource.DataClass.News;
import com.newssource.Interfaces.OnClickCallback;
import com.newssource.R;

import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<News> mNewsData;
    private OnClickCallback callback;

    public CustomAdapter(Context context, List<News> my_data) {
        this.context = context;
        this.mNewsData = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_adapter, parent, false);


        return new ViewHolder(itemView);
    }

    public void setCallBack(OnClickCallback call) {
        callback = call;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        Glide.with(context).load(mNewsData.get(position).getImage_link())
                .fitCenter().error(R.mipmap.ic_default).placeholder(R.mipmap.ic_default).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.imageView);
        holder.nameTxt.setText("" + mNewsData.get(position).getName());
        holder.mlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (callback != null) callback.OnClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView nameTxt;
        public RelativeLayout mlayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            mlayout = itemView.findViewById(R.id.mlayout);
        }
    }
}