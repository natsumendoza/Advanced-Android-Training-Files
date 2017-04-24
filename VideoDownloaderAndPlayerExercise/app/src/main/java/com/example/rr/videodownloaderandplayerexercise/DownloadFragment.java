package com.example.rr.videodownloaderandplayerexercise;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jay-Ar Gabriel on 4/24/2017.
 */

public class DownloadFragment extends Fragment implements View.OnClickListener {
    private DownloadManager mgr=null;
    private long lastDownload=-1L;
    private View start=null;
    private View viewVideo = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mgr = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        View result = inflater.inflate(R.layout.activity_main, container, false);

        start = result.findViewById(R.id.startDownload);
        start.setOnClickListener(this);

        viewVideo = result.findViewById(R.id.viewDownload);
        viewVideo.setOnClickListener(this);

        return(result);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v == start) {
            startDownload(v);
        } else if(v == viewVideo) {
            playVideo();
        }
    }

    private void startDownload(View view) {

        Uri uri = Uri.parse("http://s3-ap-southeast-1.amazonaws.com/tgs-book-share/video.mp4");

        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs();

        DownloadManager.Request req = new DownloadManager.Request(uri);

        req.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
            .setAllowedOverRoaming(false)
            .setTitle("Download")
            .setDescription("Useful")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "video.mp4");

        lastDownload = mgr.enqueue(req);

        view.setEnabled(false);

    }

    private void playVideo() {
        Intent i = new Intent(getActivity(), ViewVideo.class);
        startActivity(i);
    }

}
