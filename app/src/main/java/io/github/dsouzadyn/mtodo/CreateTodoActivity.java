package io.github.dsouzadyn.mtodo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.github.dsouzadyn.mtodo.Data.TodoContract;
import io.github.dsouzadyn.mtodo.Data.TodoDBHelper;

public class CreateTodoActivity extends ActionBarActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;

    private Spinner prioritySpinner;

    private FloatingActionButton createTodoFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SQLiteDatabase db = new TodoDBHelper(this).getWritableDatabase();
        setContentView(R.layout.activity_create_todo);

        titleEditText = (EditText) findViewById(R.id.titleEditText);

        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);

        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);

        createTodoFab = (FloatingActionButton) findViewById(R.id.createTodoFab);

        createTodoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String priority = prioritySpinner.getSelectedItem().toString();
                if(!title.isEmpty() && !description.isEmpty()) {
                    SQLiteStatement sqLiteStatement = db.compileStatement(
                            "INSERT INTO "+
                                    TodoContract.TABLE+
                                    " ( "+
                                    TodoContract.Columns.TODO_TITLE+
                                    ","+
                                    TodoContract.Columns.TODO_DESCRIPTION+
                                    ","+
                                    TodoContract.Columns.TODO_PRIOIRITY+
                                    " ) "+
                                    " VALUES ( ?,?,? ) "
                    );
                    sqLiteStatement.bindString(1,title);
                    sqLiteStatement.bindString(2,description);
                    sqLiteStatement.bindString(3,priority);
                    sqLiteStatement.executeInsert();
                    Toast.makeText(v.getContext(), "Todo Successfully Created", Toast.LENGTH_LONG).show();
                    titleEditText.setText("");
                    descriptionEditText.setText("");
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
