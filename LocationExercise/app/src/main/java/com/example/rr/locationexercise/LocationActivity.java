package com.example.rr.locationexercise;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    boolean isGpsEnabled = false;

    boolean isNetworkEnabled = false;

    double latitude;
    double longitude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

    private static final long MIN_TIME_BW_UPDATES = 400;

    protected LocationManager locationManager;

    private Button getLocationButton;

    private TextView latitudeValue;
    private TextView longitudeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationManager = (LocationManager)  getSystemService(Context.LOCATION_SERVICE);

        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        getLocationButton = (Button) findViewById(R.id.getLocationButton);

        final Location location = null;

        latitudeValue = (TextView) findViewById(R.id.latitudeValue);
        longitudeValue = (TextView) findViewById(R.id.longitudeValue);

        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGpsEnabled || isNetworkEnabled) {
                    getLocation(location);

                    Log.d(getClass().getSimpleName(), "Latitude: " + latitude);
                    Log.d(getClass().getSimpleName(), "Longitude: " + longitude);
                } else {
                    Toast.makeText(LocationActivity.this, "Please enable network and gps to continue.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void getLocation(Location loc) {



        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            if (locationManager != null) {
                loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if (loc != null) {
                    latitude = loc.getLatitude();
                    longitude = loc.getLongitude();

//                    Toast.makeText(this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_LONG).show();

                }

            }

        }

        if (isGpsEnabled) {
            if (loc == null) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this
                );
                if (locationManager != null) {
                    loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if(loc != null) {
                        latitude = loc.getLatitude();
                        longitude = loc.getLongitude();

//                        Toast.makeText(this, "Latitude: " + latitude + ", Longitude: " + longitude, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

        latitudeValue.setText(String.valueOf(latitude));
        longitudeValue.setText(String.valueOf(longitude));

    }

    public boolean canGetLocation() {
        return isGpsEnabled && isNetworkEnabled;
    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
