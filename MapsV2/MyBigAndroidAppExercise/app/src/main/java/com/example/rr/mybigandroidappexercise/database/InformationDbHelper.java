package com.example.rr.mybigandroidappexercise.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rr.mybigandroidappexercise.model.Info;

import java.util.ArrayList;

/**
 * Created by Jay-Ar Gabriel on 4/27/2017.
 */

public class InformationDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Information.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + InformationContract.InformationEntry.TABLE_NAME + " (" +
                    InformationContract.InformationEntry._ID + " INTEGER PRIMARY KEY," +
                    InformationContract.InformationEntry.COLUMN_NAME_FIRST_NAME + " TEXT," +
                    InformationContract.InformationEntry.COLUMN_NAME_LAST_NAME + " TEXT," +
                    InformationContract.InformationEntry.COLUMN_NAME_ADDRESS + " TEXT," +
                    InformationContract.InformationEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    InformationContract.InformationEntry.COLUMN_NAME_TELEPHONE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + InformationContract.InformationEntry.TABLE_NAME;

    public InformationDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public long insertInfo(Info info) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InformationContract.InformationEntry.COLUMN_NAME_FIRST_NAME, info.getFirstName());
        values.put(InformationContract.InformationEntry.COLUMN_NAME_LAST_NAME, info.getLastName());
        values.put(InformationContract.InformationEntry.COLUMN_NAME_ADDRESS, info.getAddress());
        values.put(InformationContract.InformationEntry.COLUMN_NAME_TELEPHONE, info.getTelephone());
        values.put(InformationContract.InformationEntry.COLUMN_NAME_EMAIL, info.getEmail());

        return db.insert(InformationContract.InformationEntry.TABLE_NAME, null, values);

    }

    public ArrayList<Info> getInfos() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                InformationContract.InformationEntry._ID,
                InformationContract.InformationEntry.COLUMN_NAME_FIRST_NAME,
                InformationContract.InformationEntry.COLUMN_NAME_LAST_NAME,
                InformationContract.InformationEntry.COLUMN_NAME_ADDRESS,
                InformationContract.InformationEntry.COLUMN_NAME_TELEPHONE,
                InformationContract.InformationEntry.COLUMN_NAME_EMAIL
        };

        ArrayList<Info> infos = new ArrayList<>();

        Cursor cursor = db.query(
                InformationContract.InformationEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            String id = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry._ID));
            String firstName = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_FIRST_NAME));
            String lastName = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_LAST_NAME));
            String address = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_ADDRESS));
            String telephone = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_TELEPHONE));
            String email = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_EMAIL));

            infos.add(new Info(id, firstName, lastName, address, telephone, email));
        }

        return infos;

    }

    public Info getInfo(String id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                InformationContract.InformationEntry._ID,
                InformationContract.InformationEntry.COLUMN_NAME_FIRST_NAME,
                InformationContract.InformationEntry.COLUMN_NAME_LAST_NAME,
                InformationContract.InformationEntry.COLUMN_NAME_ADDRESS,
                InformationContract.InformationEntry.COLUMN_NAME_TELEPHONE,
                InformationContract.InformationEntry.COLUMN_NAME_EMAIL
        };

        String selection = InformationContract.InformationEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(
                InformationContract.InformationEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String id2 = null;
        String firstName = null;
        String lastName = null;
        String telephone = null;
        String email = null;
        String address = null;

        while(cursor.moveToNext()) {
            id2 = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry._ID));
            firstName = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_FIRST_NAME));
            lastName = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_LAST_NAME));
            address = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_ADDRESS));
            telephone = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_TELEPHONE));
            email = cursor.getString(
                    cursor.getColumnIndex(InformationContract.InformationEntry.COLUMN_NAME_EMAIL));
        }

        cursor.close();

        return new Info(id2, firstName, lastName, address, telephone, email);

    }

}
