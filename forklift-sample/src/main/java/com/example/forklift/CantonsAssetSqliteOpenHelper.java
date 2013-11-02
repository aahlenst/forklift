package com.example.forklift;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import ch.gluecode.forklift.AbstractAssetSqliteOpenHelper;

public class CantonsAssetSqliteOpenHelper extends AbstractAssetSqliteOpenHelper {

    private static final String ASSET_NAME = "cantons.db";

    private static final String DATABASE_NAME = "cantons.db";

    private static final int DATABASE_VERSION = 1;

    public CantonsAssetSqliteOpenHelper(Context context) {
        super(context, ASSET_NAME, DATABASE_NAME, DATABASE_VERSION, null);
    }

    @Override
    protected void onConfigure(SQLiteDatabase db) {
        // do nothing
    }

    @Override
    protected void onPostCopy(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing
    }
}
