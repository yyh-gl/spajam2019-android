package com.isdl.spajam2019.Main.Fragment.Cross;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.R;

import java.util.List;

public class CrossAdapter extends RecyclerView.Adapter<CrossViewHolder> {
    private List<Music> crossMusics;

    public CrossAdapter(List<Music> crossMusics) {
        this.crossMusics = crossMusics;
    }

    @Override
    public CrossViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cross_music, parent, false);
        CrossViewHolder vh = new CrossViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(CrossViewHolder holder, int position) {
        holder.musicTitleView.setText(crossMusics.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return crossMusics.size();
    }
}
