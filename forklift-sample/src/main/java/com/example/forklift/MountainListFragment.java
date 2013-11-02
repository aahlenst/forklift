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

public class MountainListFragment extends ListFragment {
    private MountainCursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new MountainCursorAdapter(getActivity(), null, 0);
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
            MountainsResourceSqliteOpenHelper helper = new MountainsResourceSqliteOpenHelper(getActivity());
            SQLiteDatabase db = helper.getDatabase();

            SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
            builder.setTables("mountains");
            return builder.query(db, null, null, null, null, null, "height desc");
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            adapter.changeCursor(cursor);
        }
    }
}
