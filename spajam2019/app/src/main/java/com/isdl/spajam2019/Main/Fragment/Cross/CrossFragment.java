package com.isdl.spajam2019.Main.Fragment.Cross;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrossFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrossFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrossFragment extends Fragment implements CrossContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    CrossPresenter crossPresenter;

    private List<Music> musicList;

    public CrossFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrossFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrossFragment newInstance() {
        return new CrossFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        View root = inflater.inflate(R.layout.fragment_cross, container, false);
        Context context = root.getContext();


        RecyclerView rv = (RecyclerView) root.findViewById(R.id.crossRecyclerView);
        crossPresenter.getPossessedCrossMusic(2);

        CrossAdapter adapter = new CrossAdapter(musicList);

        LinearLayoutManager llm = new LinearLayoutManager(context);

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

        return root;


    }

    @Override
    public void setDataList(List<Music> possessedMusics) {
        musicList = possessedMusics;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public List<User> createDataset() {
        List<User> dataset = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName("カサレアル　太郎" + i + "号");

            dataset.add(user);
        }
        return dataset;
    }
}
