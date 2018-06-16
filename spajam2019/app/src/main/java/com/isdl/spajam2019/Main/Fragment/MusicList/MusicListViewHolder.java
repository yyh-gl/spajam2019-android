package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.isdl.spajam2019.R;

public class MusicListViewHolder extends RecyclerView.ViewHolder {
    public TextView artistNameTextView;
    public TextView musicTitleView;
    public Button musicPlayButton;
    public Button musicStopButton;

    public MusicListViewHolder(View itemView) {
        super(itemView);
        artistNameTextView = (TextView) itemView.findViewById(R.id.artistName);
        musicTitleView = (TextView) itemView.findViewById(R.id.musicTitle);
        musicPlayButton = (Button) itemView.findViewById(R.id.buttonPlay);
        musicStopButton = (Button) itemView.findViewById(R.id.buttonStop);
    }
}
