package uk.co.dekoorb.android.learning.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by edbrook on 02/10/2017.
 */

public class TodoOpenHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "todos.db";

    public static final int DATABASE_VERSION = 1;

    public TodoOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + TodoContract.Todo.TABLE_NAME + " (" +
                TodoContract.Todo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TodoContract.Todo.COLUMN_TITLE + " TEXT NOT NULL, " +
                TodoContract.Todo.COLUMN_NOTE + " TEXT NOT NULL, " +
                TodoContract.Todo.COLUMN_COMPLETED + " INTEGER NOT NULL DEFAULT 0" +
                ")";
        sqLiteDatabase.execSQL(SQL_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TodoContract.Todo.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
