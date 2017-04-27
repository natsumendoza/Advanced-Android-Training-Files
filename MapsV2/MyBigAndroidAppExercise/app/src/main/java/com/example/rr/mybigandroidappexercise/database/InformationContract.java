package com.example.rr.mybigandroidappexercise.database;

import android.provider.BaseColumns;

/**
 * Created by Jay-Ar Gabriel on 4/27/2017.
 */

public final class InformationContract {

    private InformationContract() {

    }

    public static class InformationEntry implements BaseColumns {
        public static final String TABLE_NAME = "information";
        public static final String COLUMN_NAME_FIRST_NAME = "firstname";
        public static final String COLUMN_NAME_LAST_NAME = "lastname";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_TELEPHONE = "telephone";
    }

}
