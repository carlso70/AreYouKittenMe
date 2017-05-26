package com.example.jimmy.areyoukittenme.Fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.jimmy.areyoukittenme.R;
import com.example.jimmy.areyoukittenme.database.DbClient;
import com.example.jimmy.areyoukittenme.networking.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * List Fragment containing a list of facts about cats
 */
public class FactsFragment extends ListFragment {

    private WaveSwipeRefreshLayout refreshLayout;
    public static final int QUERY_SIZE = 1000;

    public FactsFragment() {
        // Required empty public constructor
    }

    public static FactsFragment newInstance() {
        FactsFragment fragment = new FactsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fact = (String) parent.getItemAtPosition(position);
                createSaveDialog(fact);
            }
        });

        new RetrieveCatFactsTask().execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facts, container, false);

        refreshLayout = (WaveSwipeRefreshLayout) view.findViewById(R.id.ptr_facts_layout);
        refreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        refreshLayout.setWaveColor(Color.rgb(63,81,181)); // Same as @color/primary

        refreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                new RetrieveCatFactsTask().execute();
            }
        });

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

    public void createSaveDialog(final String fact) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Add the buttons
        builder.setTitle("Save this clawsome fact?");
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                DbClient.saveFact(fact, getContext());
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class RetrieveCatFactsTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            List<String> facts = new ArrayList<>();
            ApiUtils.getFacts(facts, QUERY_SIZE);
            return facts;
        }

        @Override
        protected void onPostExecute(List<String> facts) {
            if (facts != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, facts);
                setListAdapter(adapter);
            }
            refreshLayout.setRefreshing(false);
        }
    }
}
