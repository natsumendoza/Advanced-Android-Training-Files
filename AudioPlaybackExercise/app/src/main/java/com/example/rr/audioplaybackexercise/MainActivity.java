package com.example.rr.audioplaybackexercise;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mp;
    private MenuItem play;
    private MenuItem pause;
    private MenuItem stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
//            mp = MediaPlayer.create(this, R.raw.darius); this is for raw
            mp = new MediaPlayer(); // this is for assets, etc
            AssetFileDescriptor descriptor = getAssets().openFd("darius.mp3"); // comment this if you use raw file
            mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());// comment this if you use raw file
            mp.setOnCompletionListener(this);
            mp.prepare(); // comment this if you use raw file
        } catch (Exception e) {
            goBlooey(e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mp.isPlaying()) {
            mp.stop();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        play = menu.findItem(R.id.play);
        pause = menu.findItem(R.id.pause);
        stop = menu.findItem(R.id.stop);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play:
                play();
                return true;

            case R.id.pause:
                pause();
                return true;

            case R.id.stop:
                stop();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void play() {
        mp.start();

        play.setVisible(false);
        pause.setVisible(true);
        stop.setVisible(true);
    }

    private void stop() {
        mp.stop();

        findViewById(android.R.id.content).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mp.prepare();
                    mp.seekTo(0);
                    play.setVisible(true);
                } catch (Exception e) {
                    goBlooey(e);
                }
            }
        }, 100);
    }

    private void pause() {
        mp.pause();

        play.setVisible(true);
        pause.setVisible(false);
        stop.setVisible(true);
    }

    private void goBlooey(Exception e) {
        Log.e(getClass().getSimpleName(), getString(R.string.msg_error),
                e);
        Toast
                .makeText(this, R.string.msg_error_toast, Toast.LENGTH_LONG)
                .show();
    }

}
