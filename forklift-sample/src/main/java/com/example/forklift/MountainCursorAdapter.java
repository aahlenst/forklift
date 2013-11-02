package com.example.forklift;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MountainCursorAdapter extends CursorAdapter {

    public MountainCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.name = (TextView) view.findViewById(R.id.name);
        viewHolder.description = (TextView) view.findViewById(R.id.description);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.name.setText(cursor.getString(cursor.getColumnIndex("name")));
        viewHolder.description.setText(context.getString(R.string.mountain_height,
                cursor.getInt(cursor.getColumnIndex("height"))));
    }

    private static class ViewHolder {
        TextView name;
        TextView description;
    }
}
