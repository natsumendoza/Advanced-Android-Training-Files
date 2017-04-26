package com.example.rr.mapsbasicexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MapsActivity extends AbstractMapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (readyToGo()) {
            setContentView(R.layout.map_layout);        }

    }
}
