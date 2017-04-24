package com.example.rr.videodownloaderandplayerexercise;

import android.Manifest;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AbstractPermissionActivity {

    @Override
    protected String[] getDesiredPermissions() {
        return(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }

    @Override
    protected void onPermissionDenied() {
        Toast
                .makeText(this, "need permission", Toast.LENGTH_LONG)
                .show();
        finish();
    }

    @Override
    public void onReady(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectNetwork()
                .penaltyDeath()
                .build());


        if (getFragmentManager().findFragmentById(android.R.id.content)==null) {
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content,
                            new DownloadFragment()).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
