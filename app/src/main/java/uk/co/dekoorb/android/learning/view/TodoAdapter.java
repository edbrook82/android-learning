package uk.co.dekoorb.android.learning.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import uk.co.dekoorb.android.learning.R;
import uk.co.dekoorb.android.learning.model.TodoOpenHelper;

/**
 * Created by c3469162 on 03/10/2017.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {

    public class TodoHolder extends RecyclerView.ViewHolder {
        private final TextView mTodoTitle;
        private final TextView mTodoNote;
        private final CheckBox mTodoCompleted;

        public TodoHolder(View view) {
            super(view);
            mTodoTitle = view.findViewById(R.id.tv_title);
            mTodoNote = view.findViewById(R.id.tv_note);
            mTodoCompleted = view.findViewById(R.id.cb_completed);
        }

        public String getTitle() {
            return mTodoTitle.getText().toString();
        }

        public String getNote() {
            return mTodoNote.getText().toString();
        }

        public boolean isChecked() {
            return mTodoCompleted.isChecked();
        }

        public void setTitle(String title) {
            mTodoTitle.setText(title);
        }

        public void setNote(String note) {
            mTodoNote.setText(note);
        }

        public void setChecked(boolean checked) {
            mTodoCompleted.setChecked(checked);
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
        holder.setTitle("Hello " + Integer.toString(position));
        holder.setNote("This is a test note!");
        holder.setChecked(Math.random() > 0.5);
    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
