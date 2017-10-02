package uk.co.dekoorb.android.learning.model;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by c3469162 on 02/10/2017.
 */

public final class TodoContract {
    private TodoContract() {}

    public static final String CONTENT_AUTHORITY = "uk.co.dekoorb.android.todo";

    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TODO = "todo";

    public static final class Todo implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_URI.buildUpon().appendPath(PATH_TODO).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TODO;

        public static final String CONTENT_TYPE_ITEM =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TODO;

        public static final String TABLE_NAME = "todo";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_COMPLETED = "completed";
    }

}
