package com.isdl.spajam2019.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.isdl.spajam2019.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView detailView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.title);
        detailView = (TextView) itemView.findViewById(R.id.detail);
    }
}
