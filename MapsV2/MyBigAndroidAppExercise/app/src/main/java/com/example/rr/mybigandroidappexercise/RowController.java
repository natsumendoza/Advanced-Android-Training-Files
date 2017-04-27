package com.example.rr.mybigandroidappexercise;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rr.mybigandroidappexercise.model.Info;

/**
 * Created by Jay-Ar Gabriel on 4/27/2017.
 */

public class RowController extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView name=null;
    TextView address=null;
    ImageView icon=null;
    Info info;

    RowController(View row) {
        super(row);

        name = (TextView) row.findViewById(R.id.tvName);
        address = (TextView) row.findViewById(R.id.tvAddressView);
        icon = (ImageView) row.findViewById(R.id.thumbnail);

        row.setOnClickListener(this);
    }

    void bindModel(Info info) {
        name.setText(info.getFirstName() + " " + info.getLastName());
        address.setText(info.getAddress());
        this.info = info;
        Log.d(getClass().getSimpleName(), "Address is " + info.getAddress());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), DetailsActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, info.getId());
        v.getContext().startActivity(intent);
    }

}
