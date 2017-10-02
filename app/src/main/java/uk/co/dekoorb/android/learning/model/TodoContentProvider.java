package uk.co.dekoorb.android.learning.model;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

public class TodoContentProvider extends ContentProvider {
    private static final int TODOS = 100;
    private static final int TODO_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(TodoContract.CONTENT_AUTHORITY,
                TodoContract.PATH_TODO, TODOS);
        uriMatcher.addURI(TodoContract.CONTENT_AUTHORITY,
                TodoContract.PATH_TODO + "/#", TODO_WITH_ID);
        return uriMatcher;
    }

    private TodoOpenHelper mTodoDbHelper;

    public TodoContentProvider() {
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case TODOS:
                return "vnd.android.cursor.dir" + "/" + TodoContract.CONTENT_AUTHORITY + "/" + TodoContract.PATH_TODO;
            case TODO_WITH_ID:
                return "vnd.android.cursor.item" + TodoContract.CONTENT_AUTHORITY + "/" + TodoContract.PATH_TODO;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mTodoDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnedUri = null;
        switch (match) {
            case TODOS:
                long id = db.insert(TodoContract.Todo.TABLE_NAME, null, values);
                if (id > 0) {
                    returnedUri = ContentUris.withAppendedId(
                            TodoContract.Todo.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnedUri;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mTodoDbHelper = new TodoOpenHelper(context);
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mTodoDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor = null;
        switch (match) {
            case TODOS:
                retCursor = db.query(
                        TodoContract.Todo.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODOS: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
