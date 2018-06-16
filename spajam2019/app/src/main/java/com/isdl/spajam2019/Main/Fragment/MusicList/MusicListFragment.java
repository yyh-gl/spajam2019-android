package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Main.MainActivity;
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
    private final Handler handler = new Handler();
    private ProgressDialog progressDialog;

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

        progressDialog = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        showLoadingDialog("","通信中");

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_music_list, container, false);
        context = root.getContext();


        rv = (RecyclerView) root.findViewById(R.id.musicListRecyclerView);
        musicListPresenter.getPossessedCrossMusic(MainActivity.getUserId());

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

        dismissLoadingDialog();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.buttonPlay:
                musicListPresenter.audioPlay(getActivity(),position);
                break;
            case R.id.buttonStop:
                musicListPresenter.audioStop();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void showLoadingDialog(final String title, final String message) {
        handler.post(() -> {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        });
    }

    public void dismissLoadingDialog() {
        progressDialog.dismiss();
    }

}
