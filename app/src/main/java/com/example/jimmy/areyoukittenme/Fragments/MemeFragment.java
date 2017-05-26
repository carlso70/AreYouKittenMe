package com.example.jimmy.areyoukittenme.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.jimmy.areyoukittenme.Meme;
import com.example.jimmy.areyoukittenme.MemeAdapter;
import com.example.jimmy.areyoukittenme.R;
import com.example.jimmy.areyoukittenme.networking.ApiUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment containing a list of dank memes
 */
public class MemeFragment extends Fragment {

    private RecyclerView rv;

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
        View view = inflater.inflate(R.layout.fragment_meme, container, false);
        rv = (RecyclerView) view.findViewById(R.id.meme_rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        new RetrieveMemesTask().execute();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    class RetrieveMemesTask extends AsyncTask<Void, Void, List<Meme>> {
        @Override
        protected List<Meme> doInBackground(Void... params) {
            List<Meme> memes = new ArrayList<>();
            ApiUtils.getMemes(memes);
            return memes;
        }

        @Override
        protected void onPostExecute(List<Meme> memes) {
            if (memes != null) {
                MemeAdapter adapter = new MemeAdapter(memes, getContext());
                rv.setAdapter(adapter);
            }
        }
    }
}
