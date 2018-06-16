package com.isdl.spajam2019.Main.Fragment.Cross;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.isdl.spajam2019.R;

public class CrossViewHolder extends RecyclerView.ViewHolder {
    public TextView musicTitleView;
    public TextView artistNameTextView;

    public CrossViewHolder(View itemView) {
        super(itemView);
        musicTitleView = (TextView) itemView.findViewById(R.id.musicTitle);
        artistNameTextView = (TextView) itemView.findViewById(R.id.artistName);
    }
}
