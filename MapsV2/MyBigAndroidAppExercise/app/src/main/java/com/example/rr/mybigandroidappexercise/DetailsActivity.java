package com.example.rr.mybigandroidappexercise;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rr.mybigandroidappexercise.database.InformationDbHelper;
import com.example.rr.mybigandroidappexercise.model.Info;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class DetailsActivity extends AbstractMapActivity implements OnMapReadyCallback{

    private TextView tvFirstName, tvLastName, tvAddress, tvTelephone, tvEmail;

    private InformationDbHelper infoDb;
    private Info info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (readyToGo()) {
            setContentView(R.layout.activity_details);

            MapFragment mapFrag=
                    (MapFragment)getFragmentManager().findFragmentById(R.id.map);

            mapFrag.setRetainInstance(true);
            mapFrag.getMapAsync(this);
        }

        infoDb = new InformationDbHelper(this);

        info = infoDb.getInfo(getIntent().getStringExtra(Intent.EXTRA_TEXT));

        tvFirstName = (TextView) findViewById(R.id.tvFirstNameVal);
        tvLastName = (TextView) findViewById(R.id.tvLastNameVal);
        tvAddress = (TextView) findViewById(R.id.tvAddressVal);
        tvTelephone = (TextView) findViewById(R.id.tvTelephoneVal);
        tvEmail = (TextView) findViewById(R.id.tvEmailVal);

        tvFirstName.setText(info.getFirstName());
        tvLastName.setText(info.getLastName());
        tvAddress.setText(info.getAddress());
        tvTelephone.setText(info.getTelephone());
        tvEmail.setText(info.getEmail());

        tvTelephone.setOnClickListener(new TelephoneClickListener());
        tvEmail.setOnClickListener(new EmailClickListener());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Geocoder geocoder = new Geocoder(this);

        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocationName(info.getAddress(), 1);

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error Geocoder ", e);
        }
        Log.d(getClass().getSimpleName(), "Address: " + info.getAddress()  +" " + addresses);


        if (addresses != null) {

            Address address = addresses.get(0);

            double lat = address.getLatitude();
            double lon = address.getLongitude();

            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(lat,
                            lon));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(20);

            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);

            addMarker(googleMap, lat, lon, info.getAddress());

        }

    }

    private void addMarker(GoogleMap map, double lat, double lon,
                           String title) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                .title(title));
    }

    class TelephoneClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String toDial = "tel:" + info.getTelephone();
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
        }
    }

    class EmailClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, info.getEmail());
            startActivity(intent);
        }
    }

}
