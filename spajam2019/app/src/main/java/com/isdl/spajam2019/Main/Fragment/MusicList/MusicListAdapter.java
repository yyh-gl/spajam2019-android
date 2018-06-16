package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.R;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicListViewHolder>{
    private List<Music> musics;
    private static MusicListAdapter.OnItemClickListener listener;


    public MusicListAdapter(List<Music> musics) {
        this.musics = musics;
    }

    public void setOnItemClickListener(MusicListAdapter.OnItemClickListener listener) {
        MusicListAdapter.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public MusicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_music, parent, false);
        MusicListViewHolder vh = new MusicListViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(MusicListViewHolder holder, int position) {
        holder.artistNameTextView.setText(musics.get(position).getName());
        holder.musicTitleView.setText(musics.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    static class MusicListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView artistNameTextView;
        public TextView musicTitleView;
        public ImageButton musicPlayButton;
        public ImageButton musicStopButton;

        public MusicListViewHolder(View itemView) {
            super(itemView);
            artistNameTextView = (TextView) itemView.findViewById(R.id.artistName);
            musicTitleView = (TextView) itemView.findViewById(R.id.musicTitle);
            musicPlayButton = (ImageButton) itemView.findViewById(R.id.buttonPlay);
            musicStopButton = (ImageButton) itemView.findViewById(R.id.buttonStop);

            musicPlayButton.setOnClickListener(this);
            musicStopButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, getLayoutPosition());
            }
        }

    }
}
