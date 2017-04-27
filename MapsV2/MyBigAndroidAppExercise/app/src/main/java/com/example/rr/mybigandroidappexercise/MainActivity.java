package com.example.rr.mybigandroidappexercise;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rr.mybigandroidappexercise.database.InformationDbHelper;
import com.example.rr.mybigandroidappexercise.model.Info;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etAddress, etTelephone, etEmail;
    private Button btnAdd, btnClear, btnView;

    private InformationDbHelper infoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etTelephone = (EditText) findViewById(R.id.etTelephone);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnView = (Button) findViewById(R.id.btnView);

        btnAdd.setOnClickListener(new AddButtonClickListener());
        btnClear.setOnClickListener(new ClearButtonClickListener());
        btnView.setOnClickListener(new ViewButtonClickListener());

        infoDb = new InformationDbHelper(this);

    }

    class AddButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Info info = new Info(
                    null,
                    etFirstName.getText().toString(),
                    etLastName.getText().toString(),
                    etAddress.getText().toString(),
                    etTelephone.getText().toString(),
                    etEmail.getText().toString()
            );

            long newRowId = infoDb.insertInfo(info);

            Log.d(getClass().getSimpleName(), "ID of newly inserted row is: " + newRowId);

        }
    }

    class ClearButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            etFirstName.setText("");
            etLastName.setText("");
            etAddress.setText("");
            etTelephone.setText("");
            etEmail.setText("");
        }
    }

    class ViewButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
            startActivity(intent);
        }
    }

}
