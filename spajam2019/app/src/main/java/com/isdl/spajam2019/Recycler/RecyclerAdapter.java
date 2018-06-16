package com.isdl.spajam2019.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<User> users;

    public RecyclerAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        RecyclerViewHolder vh = new RecyclerViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
