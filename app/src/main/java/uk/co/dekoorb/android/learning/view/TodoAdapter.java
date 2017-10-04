package uk.co.dekoorb.android.learning.view;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import uk.co.dekoorb.android.learning.R;
import uk.co.dekoorb.android.learning.model.TodoContract;

/**
 * Created by c3469162 on 03/10/2017.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {

    private Cursor mCursor;
    private int mTitleIndex;
    private int mNoteIndex;
    private int mCompletedIndex;

    class TodoHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView note;
        final CheckBox completed;

        TodoHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            note = view.findViewById(R.id.tv_note);
            completed = view.findViewById(R.id.cb_completed);
        }
    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new TodoHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
        if (mCursor.moveToPosition(position)) {
            holder.title.setText(mCursor.getString(mTitleIndex));
            holder.note.setText(mCursor.getString(mNoteIndex));
            holder.completed.setChecked(mCursor.getInt(mCompletedIndex) == 1);
        }
    }

    public void swapCursor(Cursor cursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = cursor;
        mTitleIndex = mCursor.getColumnIndex(TodoContract.Todo.COLUMN_TITLE);
        mNoteIndex = mCursor.getColumnIndex(TodoContract.Todo.COLUMN_NOTE);
        mCompletedIndex = mCursor.getColumnIndex(TodoContract.Todo.COLUMN_COMPLETED);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mCursor != null) {
            count = mCursor.getCount();
        }
        return count;
    }
}
