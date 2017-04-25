package com.example.rr.videodownloaderandplayerexercise;

import android.Manifest;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by Jay-Ar Gabriel on 4/24/2017.
 */

public class ViewVideo extends AbstractPermissionActivity {

    private VideoView video;
    private MediaController ctlr;

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
    public void onReady(Bundle icicle) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.video);

        File clip=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "video.mp4");

        if (clip.exists()) {
            video=(VideoView)findViewById(R.id.video);
            video.setVideoPath(clip.getAbsolutePath());

            ctlr= new MediaController(this);
            ctlr.setMediaPlayer(video);
            video.setMediaController(ctlr);
            video.requestFocus();
            video.start();

            Log.d(getClass().getSimpleName(), "path is " + clip.getAbsolutePath());
        } else {
            Log.d(getClass().getSimpleName(), "file does not exists!");
        }
    }
}
