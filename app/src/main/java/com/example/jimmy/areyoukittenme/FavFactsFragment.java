package com.example.jimmy.areyoukittenme;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.jimmy.areyoukittenme.database.DbClient;
import com.example.jimmy.areyoukittenme.networking.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


/**
 * List Showing favorite facts
 */
public class FavFactsFragment extends ListFragment implements OnRefreshListener {

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

        refreshLayout = (PullToRefreshLayout) view.findViewById(R.id.ptr_favs_layout);
        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
                // Mark All Children as pullable
                .allChildrenArePullable()
                // Set a OnRefreshListener
                .listener(this)
                // Finally commit the setup to our PullToRefreshLayout
                .setup(refreshLayout);

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
                // retrieve the new list and set it to the adapter
                new RetrieveFavFactsTask().execute();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRefreshStarted(View view) {
        new RetrieveFavFactsTask().execute();
    }

    class RetrieveFavFactsTask extends AsyncTask<Void, Void, Void> {
        List<String> facts = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {
            DbClient.getFacts(facts, getContext());
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            if (facts != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, facts);
                setListAdapter(adapter);
            }
            refreshLayout.setRefreshComplete();
        }
    }
}
