package com.example.rr.listsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareSensorData();
    }

    private void prepareSensorData() {
        String serviceName = Context.SENSOR_SERVICE;
        SensorManager sensorManager = (SensorManager) getSystemService(serviceName);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        
        if (sensorList == null) {
            Log.d(TAG, "prepareSensorData: I didn't get any sensor data");
        } else {
            for (Sensor sensor : sensorList) {
                Log.d(TAG, "prepareSensorData: " + String.format("Sensor name: %s, Sensor vendor: %s", sensor.getName(), sensor.getVendor()));
            }
        }
    }

}
