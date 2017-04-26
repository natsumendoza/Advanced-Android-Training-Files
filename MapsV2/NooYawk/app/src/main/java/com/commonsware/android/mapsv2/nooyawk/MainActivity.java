/***
 Copyright (c) 2012 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package com.commonsware.android.mapsv2.nooyawk;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MainActivity extends AbstractMapActivity implements
    OnMapReadyCallback, AdapterView.OnItemSelectedListener {

  private Spinner spinner;
  CameraUpdate center = null;

  MapFragment mapFragment;

  GoogleMap googleMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (readyToGo()) {
      setContentView(R.layout.activity_main);

      spinner = (Spinner) findViewById(R.id.spinner);
      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
              R.array.locations, android.R.layout.simple_spinner_item);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(adapter);


      mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

      spinner.setOnItemSelectedListener(this);

      center =
              CameraUpdateFactory.newLatLng(new LatLng(40.76793169992044,
                      -73.98180484771729));

      MapFragment mapFrag=
          (MapFragment)getFragmentManager().findFragmentById(R.id.map);

      if (savedInstanceState == null) {
        mapFrag.getMapAsync(this);
      }
    }
  }

  @Override
  public void onMapReady(GoogleMap map) {

    googleMap = map;

    CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    map.moveCamera(center);
    map.animateCamera(zoom);
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    if (spinner.getSelectedItem().toString().equals("Fluxion Inc.")) {
      Log.d(getClass().getSimpleName(), "You selected " + spinner.getSelectedItem().toString());

      Geocoder geocoder = new Geocoder(this);
      List<Address> addresses = null;

      try {
        addresses = geocoder.getFromLocationName("F1 Hotel Manila", 5);

      } catch (Exception e) {
        e.printStackTrace();
      }

      if (addresses != null) {
        for (Address address : addresses) {
          Log.d(getClass().getSimpleName(), String.format("addresses long:lat %s:%s", address.getLatitude(), address.getLongitude()));
        }
      } else {
        Log.d(getClass().getSimpleName(), "addresses is empty.");
      }

      Log.d(getClass().getSimpleName(), "Addresses is " + addresses);

      center =
              CameraUpdateFactory.newLatLng(new LatLng(14.553401,
                      121.0483523));

      CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

      googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
      googleMap.moveCamera(center);
      googleMap.animateCamera(zoom);

    } else if (spinner.getSelectedItem().toString().equals("Veritown")) {
      center =
              CameraUpdateFactory.newLatLng(new LatLng(14.5590917,
                      121.049672));



      CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

      googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
      googleMap.moveCamera(center);
      googleMap.animateCamera(zoom);
    }

  }



  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
