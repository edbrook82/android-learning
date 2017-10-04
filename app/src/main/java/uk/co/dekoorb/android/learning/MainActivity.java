package uk.co.dekoorb.android.learning;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import uk.co.dekoorb.android.learning.model.TodoContract;
import uk.co.dekoorb.android.learning.view.TodoAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TodoAdapter mAdapter;
    private RecyclerView mItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemsList = (RecyclerView) findViewById(R.id.rv_todo_items);
        mItemsList.setHasFixedSize(true);
        mItemsList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new TodoAdapter();
        mItemsList.setAdapter(mAdapter);

        Button addBtn = (Button) findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.add();
                ContentValues contentValues = new ContentValues();
                contentValues.put(TodoContract.Todo.COLUMN_TITLE, "Test_" + (int) (Math.random()*1e6));
                contentValues.put(TodoContract.Todo.COLUMN_NOTE, "Just a test note");
                contentValues.put(TodoContract.Todo.COLUMN_COMPLETED, true);

                Uri uri = getContentResolver().insert(
                        TodoContract.Todo.CONTENT_URI,
                        contentValues);

                Log.d(TAG, "onClick: " + uri);
            }
        });

        Button subBtn = (Button) findViewById(R.id.btn_sub);
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.sub();

                String[] projection = new String[] {
                        TodoContract.Todo.COLUMN_TITLE,
                        TodoContract.Todo.COLUMN_NOTE,
                        TodoContract.Todo.COLUMN_COMPLETED};

                Cursor cursor = getContentResolver().query(
                        TodoContract.Todo.CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);

                if (cursor != null) {
                    int titleIndex = cursor.getColumnIndex(TodoContract.Todo.COLUMN_TITLE);
                    int noteIndex = cursor.getColumnIndex(TodoContract.Todo.COLUMN_NOTE);
                    int completedIndex = cursor.getColumnIndex(TodoContract.Todo.COLUMN_COMPLETED);

                    while (cursor.moveToNext()) {
                        String title = cursor.getString(titleIndex);
                        String note = cursor.getString(noteIndex);
                        boolean completed = cursor.getInt(completedIndex) == 1;

                        Log.d(TAG, String.format("onClick: %s, %s, %s", title, note, completed));
                    }
                }
            }
        });
    }
}
