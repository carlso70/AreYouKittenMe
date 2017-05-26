package com.example.jimmy.areyoukittenme.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.jimmy.areyoukittenme.Meme;
import com.example.jimmy.areyoukittenme.MemeAdapter;
import com.example.jimmy.areyoukittenme.R;
import com.example.jimmy.areyoukittenme.networking.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;


/**
 * Fragment containing a list of dank memes
 */
public class MemeFragment extends Fragment  {

    private RecyclerView rv;
    private WaveSwipeRefreshLayout refreshLayout;

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

        refreshLayout = (WaveSwipeRefreshLayout) view.findViewById(R.id.ptr_facts_layout);
        refreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        refreshLayout.setWaveColor(Color.rgb(63,81,181)); // Same as @color/primary

        refreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                new RetrieveMemesTask().execute();
            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        Toast toast = Toast.makeText(getContext(), "Pull down to refresh", Toast.LENGTH_LONG);
        toast.show();

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

            refreshLayout.setRefreshing(false);
        }
    }
}
