package com.example.rr.audiorecordexercise;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class MainActivity extends AbstractPermissionActivity implements
        CompoundButton.OnCheckedChangeListener, MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {

    private static final String BASENAME="recording.3gp";
    private MediaRecorder recorder=null;

    @Override
    protected String[] getDesiredPermissions() {
        return new String[] {RECORD_AUDIO, WRITE_EXTERNAL_STORAGE};
    }

    @Override
    protected void onPermissionDenied() {
        Toast
                .makeText(this, R.string.msg_sorry, Toast.LENGTH_LONG)
                .show();
        finish();
    }

    @Override
    protected void onReady(Bundle state) {
        setContentView(R.layout.activity_main);

        ((ToggleButton) findViewById(R.id.record)).setOnCheckedChangeListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        recorder = new MediaRecorder();
        recorder.setOnErrorListener(this);
        recorder.setOnInfoListener(this);
    }

    @Override
    protected void onStop() {
        recorder.release();
        recorder = null;

        super.onStop();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            String filename = Environment.getExternalStorageDirectory() + "/Downloads/" + BASENAME;
            File output=
                    new File(filename);

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(output.getAbsolutePath());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                recorder.setAudioEncodingBitRate(160 * 1024);
            }
            else {
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            }

            recorder.setAudioChannels(2);

            try {
                recorder.prepare();
                recorder.start();
            }
            catch (Exception e) {
                Log.e(getClass().getSimpleName(),
                        "Exception in preparing recorder", e);
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        else {
            try {
                recorder.stop();
            }
            catch (Exception e) {
                Log.w(getClass().getSimpleName(),
                        "Exception in stopping recorder", e);
                // can fail if start() failed for some reason
            }

            recorder.reset();
        }
    }

    @Override
    public void onInfo(MediaRecorder mr, int what, int extra) {
        String msg = getString(R.string.strange);

        switch (what) {
            case MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED:
                msg = getString(R.string.max_duration);
                break;
            case MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED:
                msg = getString(R.string.max_size);
                break;
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        Toast.makeText(this, R.string.strange, Toast.LENGTH_LONG).show();
    }
}
