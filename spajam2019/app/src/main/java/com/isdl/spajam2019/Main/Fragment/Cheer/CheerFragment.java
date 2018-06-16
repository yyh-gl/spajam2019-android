package com.isdl.spajam2019.Main.Fragment.Cheer;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheerFragment extends Fragment implements CheerContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    CheerPresenter cheerPresenter;

    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    View root;
    ImageView imageView;
    Resources res;

    public CheerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CheerFragment newInstance() {
        return new CheerFragment();
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
        root = inflater.inflate(R.layout.fragment_cheer, container, false);
//        root.setBackgroundColor((Integer) mArgbEvaluator.evaluate(0, Color.RED, Color.BLUE));
        res = getActivity().getResources();

        ImageButton buttonCheer = (ImageButton) root.findViewById(R.id.buttonCheer);
        imageView = (ImageView) root.findViewById(R.id.imageView3);
        cheerPresenter.getLiveInfo(1);

        buttonCheer.setOnClickListener(v -> {
            cheerPresenter.postLikes(1);
        });

        return root;
    }

    public void changeBackgroundColor(int like) {
//        root.setBackgroundColor((Integer) mArgbEvaluator.evaluate(like, Color.RED, Color.BLUE));
        if (like < 20) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.a));
        } else if (20 < like && like < 40) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.b));
        } else if (40 < like && like < 60) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.c));
        } else if (60 < like && like < 70) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.d));
        } else if (70 < like) {
            imageView.setImageDrawable(res.getDrawable(R.drawable.e));
        }
    }

    @Override
    public void showOtaku() {
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOtaku() {
        imageView.setVisibility(View.GONE);
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
}
