package com.example.rr.testingexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Date getFormattedDate(String dateStr) {

        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;

    }

    public String getSimpleFormattedDate(Date date) {

        String dateStr = new SimpleDateFormat("MMMM dd, y").format(date);

        return dateStr;
    }

}
