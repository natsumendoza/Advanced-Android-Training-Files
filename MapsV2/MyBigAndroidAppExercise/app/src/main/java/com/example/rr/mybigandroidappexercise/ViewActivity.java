package com.example.rr.mybigandroidappexercise;

import android.content.Intent;
import android.database.Cursor;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rr.mybigandroidappexercise.database.InformationContract;
import com.example.rr.mybigandroidappexercise.database.InformationDbHelper;
import com.example.rr.mybigandroidappexercise.model.Info;

import java.util.ArrayList;

public class ViewActivity extends RecyclerViewActivity {

    private  InformationDbHelper infoDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        infoDb = new InformationDbHelper(this);
        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new InfoAdapter());
    }

    class InfoAdapter extends RecyclerView.Adapter<RowController> {

        @Override
        public RowController onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RowController(getLayoutInflater().inflate(R.layout.row, parent, false));
        }

        @Override
        public void onBindViewHolder(RowController holder, int position) {
            holder.bindModel(infoDb.getInfos().get(position));
        }

        @Override
        public int getItemCount() {
            return infoDb.getInfos().size();
        }

    }

}
