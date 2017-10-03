package uk.co.dekoorb.android.learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import uk.co.dekoorb.android.learning.view.TodoAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemsList = (RecyclerView) findViewById(R.id.rv_todo_items);
        mItemsList.setHasFixedSize(true);
        mItemsList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mItemsList.setAdapter(new TodoAdapter());
    }
}
