package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.isdl.spajam2019.R;

public class MusicListViewHolder extends RecyclerView.ViewHolder {
    public TextView musicTitleView;

    public MusicListViewHolder(View itemView) {
        super(itemView);
        musicTitleView = (TextView) itemView.findViewById(R.id.musicTitle);
    }
}
