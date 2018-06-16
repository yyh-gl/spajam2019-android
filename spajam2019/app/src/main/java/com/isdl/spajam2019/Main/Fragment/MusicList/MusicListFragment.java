package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import java.util.List;

import javax.inject.Inject;

public class MusicListFragment extends Fragment implements MusicListContract.View,MusicListAdapter.OnItemClickListener {

    @Inject
    MusicListPresenter musicListPresenter;

    RecyclerView rv;
    MusicListAdapter adapter;
    Context context;
    MediaPlayer mediaPlayer;

    public MusicListFragment() {
        // Required empty public constructor
    }
    public static MusicListFragment newInstance() {
        return new MusicListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder()
                .appComponent(((Spajam2019Application) getActivity().getApplicationContext())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_music_list, container, false);
        context = root.getContext();


        rv = (RecyclerView) root.findViewById(R.id.musicListRecyclerView);
        musicListPresenter.getPossessedCrossMusic(2);
        //musicListPresenter.audioPlay(getActivity());


        return root;
    }


    @Override
    public void setAdapter(List<Music> possessedMusics) {
        adapter = new MusicListAdapter(possessedMusics);
        adapter.setOnItemClickListener(this);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.buttonPlay:
                Log.w("id", "" + position);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
