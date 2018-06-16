package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.R;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListViewHolder> {
    private List<Music> musics;

    public MusicListAdapter(List<Music> musics) {
        this.musics = musics;
    }

    @Override
    public MusicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_music, parent, false);
        MusicListViewHolder vh = new MusicListViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(MusicListViewHolder holder, int position) {
        holder.musicTitleView.setText(musics.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }
}
