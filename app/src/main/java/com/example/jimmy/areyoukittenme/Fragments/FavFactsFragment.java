package com.example.jimmy.areyoukittenme.Fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.jimmy.areyoukittenme.R;
import com.example.jimmy.areyoukittenme.database.DbClient;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;


/**
 * List Showing favorite facts
 */
public class FavFactsFragment extends ListFragment {

    private PullToRefreshLayout refreshLayout;

    public FavFactsFragment() {}

    public static FavFactsFragment newInstance() {
        FavFactsFragment fragment = new FavFactsFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fact = (String) parent.getItemAtPosition(position);
                createEditDialog(fact);
            }
        });
        new RetrieveFavFactsTask().execute();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fav_facts, container, false);

        return view;
    }

    private void createEditDialog(final String fact) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Add the buttons
        builder.setTitle("Delete this clawsome fact?");
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                DbClient.deleteFact(fact, getContext());

                dialog.dismiss();
                // retrieve the new list and set it to the adapter
                new RetrieveFavFactsTask().execute();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    class RetrieveFavFactsTask extends AsyncTask<Void, Void, List<String>>  {
        @Override
        protected List<String> doInBackground(Void... params) {
            List<String> facts = new ArrayList<>();
            DbClient.getFacts(facts, getContext());
            return facts;
        }

        @Override
        protected void onPostExecute(List<String> facts) {
            if (facts != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, facts);
                setListAdapter(adapter);
            }
        }
    }
}
