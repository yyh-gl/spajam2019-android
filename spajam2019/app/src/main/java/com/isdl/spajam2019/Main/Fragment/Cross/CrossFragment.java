package com.isdl.spajam2019.Main.Fragment.Cross;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Main.MainActivity;
import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import java.util.List;

import javax.inject.Inject;

public class CrossFragment extends Fragment implements CrossContract.View,CrossAdapter.OnItemClickListener {

    @Inject
    CrossPresenter crossPresenter;

    RecyclerView rv;
    CrossAdapter adapter;
    Context context;
    private final Handler handler = new Handler();
    private ProgressDialog progressDialog;

    private List<Music> musicList;

    public CrossFragment() {
        // Required empty public constructor
    }

    public static CrossFragment newInstance() {
        return new CrossFragment();
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

        showLoadingDialog("", "通信中");

        View root = inflater.inflate(R.layout.fragment_cross, container, false);
        context = root.getContext();


        rv = (RecyclerView) root.findViewById(R.id.crossRecyclerView);
        crossPresenter.getPossessedCrossMusic(MainActivity.getUserId());

        return root;
    }

    @Override
    public void setAdapter(List<Music> possessedMusics) {
        adapter = new CrossAdapter(possessedMusics);
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
                Log.w("id", "" + position);
                crossPresenter.audioPlay(getActivity(),position);
                ImageButton playButton = view.findViewById(R.id.buttonPlay);
                ImageButton stopButton = view.findViewById(R.id.buttonStop);
                playButton.setVisibility(View.INVISIBLE);
                stopButton.setVisibility(View.GONE);
                break;
            case R.id.buttonStop:
                Log.w("id", "" + position);

                crossPresenter.audioStop();
                break;
            case R.id.buttonAccept:
                break;
            case R.id.buttonDeny:
                crossPresenter.deleteCrossMusic(position);
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
