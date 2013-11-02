package com.example.forklift;

import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

public class CantonListFragment extends ListFragment {

    private SimpleCursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, null, new String[]{"name", "capital"},
                new int[]{R.id.name, R.id.description}, 0);

        setListAdapter(adapter);

        return inflater.inflate(R.layout.list, null);
    }

    @Override
    public void onResume() {
        super.onResume();

        new LoadDataTask().execute();
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            CantonsAssetSqliteOpenHelper helper = new CantonsAssetSqliteOpenHelper(getActivity());
            SQLiteDatabase db = helper.getDatabase();

            SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
            builder.setTables("cantons");
            return builder.query(db, null, null, null, null, null, "_id");
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            adapter.changeCursor(cursor);
        }
    }
}
