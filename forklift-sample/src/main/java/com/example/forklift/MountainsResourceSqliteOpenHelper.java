package com.example.forklift;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import ch.gluecode.forklift.AbstractResourceSqliteOpenHelper;

public class MountainsResourceSqliteOpenHelper extends AbstractResourceSqliteOpenHelper {

    private static final int RAW_RESOURCE = R.raw.mountains;

    private static final String DATABASE_NAME = "mountains.db";

    private static final int DATABASE_VERSION = 1;

    public MountainsResourceSqliteOpenHelper(Context context) {
        super(context, RAW_RESOURCE, DATABASE_NAME, DATABASE_VERSION);
    }

    @Override
    protected void onConfigure(SQLiteDatabase db) {
        // Do nothing
    }

    @Override
    protected void onPostCopy(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing
    }
}
