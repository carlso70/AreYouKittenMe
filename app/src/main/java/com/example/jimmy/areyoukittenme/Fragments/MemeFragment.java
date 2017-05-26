package com.example.jimmy.areyoukittenme.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jimmy.areyoukittenme.R;


/**
 * Fragment containing a list of dank memes
 */
public class MemeFragment extends Fragment {

    public MemeFragment() {
        // Required empty public constructor
    }

    public static MemeFragment newInstance() {
        MemeFragment memes = new MemeFragment();
        return memes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meme, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
