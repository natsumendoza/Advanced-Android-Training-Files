package com.example.rr.internetresdisplay;

import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView linkDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkDisplay = (TextView) findViewById(R.id.link_display);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Filxgirl.TTF");
        linkDisplay.setTypeface(face);

        // Set font from external
//        File font = new File(Environment.getExternalStorageDirectory(), "MyOpenCustomFont.ttf");
//        if (font.exists()) {
//            face = Typeface.createFromFile(font);
//            linkDisplay.setTypeface(face);
//        } else {
//
//        }

        new LoadThread().start();

    }

    class LoadThread extends Thread {

        final String LINK = "http://ip.jsontest.com/";
        final Gson gson = new Gson();

        @Override
        public void run() {

            try {

                URL url = new URL(LINK);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();

                String line;

                try {
                    InputStream in = c.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while ((line = reader.readLine()) != null) {
//                        tv.append(line.toString());
                        Log.d(getClass().getSimpleName(), "result is " + line);

                        IPModel model = gson.fromJson(reader.readLine(), IPModel.class);
                        Log.d(getClass().getSimpleName(), "converted json is " + model);
                    }

                    reader.close();

                } catch (IOException e) {
                    Log.e(getClass().getSimpleName(), "Exception Parsing LINK", e);
                } finally {
                    c.disconnect();
                }

            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Exception Parsing LINK", e);
            }

        }
    }

}
