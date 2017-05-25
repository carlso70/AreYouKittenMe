package com.example.jimmy.areyoukittenme;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Fragment that tracks the accelerometer
 */
public class MovementFragment extends Fragment implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView xView;
    private TextView yView;
    private TextView zView;
    private Button playBtn;
    private Button pauseBtn;

    public MovementFragment() {
    }

    public static MovementFragment newInstace(){
        MovementFragment fragment = new MovementFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(getContext().SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movement, container, false);
        this.xView = (TextView) view.findViewById(R.id.accel_x);
        this.yView = (TextView) view.findViewById(R.id.accel_y);
        this.zView = (TextView) view.findViewById(R.id.accel_z);
        this.playBtn = (Button) view.findViewById(R.id.playBtn);
        this.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTracking();
            }
        });

        this.pauseBtn = (Button) view.findViewById(R.id.pauseBtn);
        this.pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTracking();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause(){
        super.onPause();
        stopTracking();
    }

    public void onResume(){
        super.onResume();
        startTracking();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            xView.setText("X = " + x);
            float y = event.values[1];
            yView.setText("Y = " + y);
            float z = event.values[2];
            zView.setText("Z = " + z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void stopTracking() {
         sensorManager.unregisterListener(this);
    }

    public void startTracking() {
         sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
