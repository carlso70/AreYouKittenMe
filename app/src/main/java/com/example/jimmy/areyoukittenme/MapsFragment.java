package com.example.jimmy.areyoukittenme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.content.ContentValues.TAG;
import static com.example.jimmy.areyoukittenme.R.id.map;


/**
 *  create an instance of this fragment.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private View view;
    private MapView mapView;
    private GoogleMap myMap;
    private FloatingActionButton zoomIn;
    private FloatingActionButton zoomOut;

    public MapsFragment() {}

    public static MapsFragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            view = inflater.inflate(R.layout.fragment_maps, container, false);
            MapsInitializer.initialize(this.getActivity());

            zoomIn = (FloatingActionButton) view.findViewById(R.id.fab_zoom_in);
            zoomIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomIn();
                }
            });
            zoomOut = (FloatingActionButton) view.findViewById(R.id.fab_zoom_out);
            zoomOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    zoomOut();
                }
            });

            mapView = (MapView) view.findViewById(map);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
       }
        catch (InflateException e){
            Log.e(TAG, "Inflate exception");
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void zoomIn() {
        if (myMap != null)
            myMap.animateCamera(CameraUpdateFactory.zoomIn());

    }

    public void zoomOut() {
        if (myMap != null)
            myMap.animateCamera(CameraUpdateFactory.zoomOut());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng chicago = new LatLng(41.878, 87.629);
        myMap = googleMap;
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chicago, 15));
    }

    private boolean checkReady() {
        if (myMap == null) {
            Toast.makeText(getContext(), R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
