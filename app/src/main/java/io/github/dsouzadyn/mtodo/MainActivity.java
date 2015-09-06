package io.github.dsouzadyn.mtodo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.dsouzadyn.mtodo.Adapters.TodoRecyclerAdapter;
import io.github.dsouzadyn.mtodo.Data.TodoContract;
import io.github.dsouzadyn.mtodo.Data.TodoDBHelper;

public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton fab;

    List<Todos> mTodoList = new ArrayList<>();

    public void updateUI() {
        mTodoList.clear();
        SQLiteDatabase db = new TodoDBHelper(this).getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM "+
                        TodoContract.TABLE+
                " ORDER BY "+
                TodoContract.Columns.TODO_PRIOIRITY+
                " DESC",
                null
        );

        cursor.moveToFirst();
        while(cursor.moveToNext()) {
            mTodoList.add(new Todos(
                    cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.Columns.TODO_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TodoContract.Columns.TODO_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(TodoContract.Columns.TODO_PRIOIRITY))
            ));
        }
        cursor.close();
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView delTitle = (TextView) v.findViewById(R.id.todoTitle);

        String title = delTitle.getText().toString();

        SQLiteDatabase db = new TodoDBHelper(MainActivity.this).getWritableDatabase();
        SQLiteStatement sqlStatement = db.compileStatement(
                "DELETE FROM "+
                        TodoContract.TABLE+
                        " WHERE "+
                        TodoContract.Columns.TODO_TITLE+
                        " = ?");
        sqlStatement.bindString(1, title);
        sqlStatement.executeUpdateDelete();
        updateUI();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializeData();

        fab = (FloatingActionButton) findViewById(R.id.addTodo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateTodoActivity.class);
                startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.todoRV);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TodoRecyclerAdapter(mTodoList);
        updateUI();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_about) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
