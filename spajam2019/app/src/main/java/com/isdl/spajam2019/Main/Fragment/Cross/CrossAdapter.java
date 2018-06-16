package com.isdl.spajam2019.Main.Fragment.Cross;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.R;

import java.util.List;

public class CrossAdapter extends RecyclerView.Adapter<CrossAdapter.CrossViewHolder> {
    private List<Music> crossMusics;
    private static CrossAdapter.OnItemClickListener listener;

    public CrossAdapter(List<Music> crossMusics) {
        this.crossMusics = crossMusics;
    }

    public void setOnItemClickListener(CrossAdapter.OnItemClickListener listener) {
        CrossAdapter.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public CrossViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cross_music, parent, false);
        CrossViewHolder vh = new CrossViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(CrossViewHolder holder, int position) {
        holder.artistNameTextView.setText((crossMusics.get(position).getArtist()));
        holder.musicTitleView.setText(crossMusics.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return crossMusics.size();
    }

    public class CrossViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView musicTitleView;
        public TextView artistNameTextView;
        public ImageButton acceptButton;
        public ImageButton denyButton;
        public ImageButton playButton;
        public ImageButton stopButton;

        public CrossViewHolder(View itemView) {
            super(itemView);
            musicTitleView = (TextView) itemView.findViewById(R.id.musicTitle);
            artistNameTextView = (TextView) itemView.findViewById(R.id.artistName);
            acceptButton = (ImageButton) itemView.findViewById(R.id.buttonAccept);
            denyButton = (ImageButton) itemView.findViewById(R.id.buttonDeny);
            playButton = (ImageButton) itemView.findViewById(R.id.buttonPlay);
            stopButton = (ImageButton) itemView.findViewById(R.id.buttonStop);

            acceptButton.setOnClickListener(this);
            denyButton.setOnClickListener(this);
            playButton.setOnClickListener(this);
            stopButton.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}
