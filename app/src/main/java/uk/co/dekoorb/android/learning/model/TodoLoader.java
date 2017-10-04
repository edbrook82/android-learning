package uk.co.dekoorb.android.learning.model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

/**
 * Created by c3469162 on 04/10/2017.
 */

public class TodoLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private TodoLoaderCallbacks mCallbacks;

    public interface TodoLoaderCallbacks {
        void onLoadComplete(Cursor cursor);
    }

    public TodoLoader(Context context, TodoLoaderCallbacks callbacks) {
        mContext = context;
        mCallbacks = callbacks;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = TodoContract.Todo.CONTENT_URI;

        String[] projection = new String[] {
                TodoContract.Todo.COLUMN_TITLE,
                TodoContract.Todo.COLUMN_NOTE,
                TodoContract.Todo.COLUMN_COMPLETED
        };

        return new CursorLoader(
                mContext,
                uri,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCallbacks.onLoadComplete(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
