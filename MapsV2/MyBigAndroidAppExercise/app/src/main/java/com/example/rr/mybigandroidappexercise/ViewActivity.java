package com.example.rr.mybigandroidappexercise;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rr.mybigandroidappexercise.database.InformationContract;
import com.example.rr.mybigandroidappexercise.database.InformationDbHelper;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ListView listView;
    private InformationDbHelper infoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listView = (ListView) findViewById(R.id.info_list);
        infoDb = new InformationDbHelper(this);

        Cursor cursor = infoDb.getInfos();
        ArrayList<String> infos = new ArrayList<>();
        while(cursor.moveToNext()) {

            String firstName = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_FIRST_NAME));
            long id = cursor.getLong(cursor.getColumnIndex(InformationContract.InformationEntry._ID));
            infos.add(firstName);

        }

        listView.setOnItemClickListener(new ListViewItemClickListener());

        cursor.close();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infos);
        listView.setAdapter(adapter);

    }

    class ListViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(id + 1));
            startActivity(intent);

        }
    }

}
