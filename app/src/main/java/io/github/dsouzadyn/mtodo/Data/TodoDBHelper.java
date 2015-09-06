package io.github.dsouzadyn.mtodo.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DYLAN on 15-08-2015.
 */
public class TodoDBHelper extends SQLiteOpenHelper {

    public TodoDBHelper(Context context) {
        super(context, TodoContract.DB_NAME, null, TodoContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = String.format("CREATE TABLE %s (" +
        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "%s TEXT, " +
        "%s TEXT, " +
        "%s TEXT)", TodoContract.TABLE
                ,TodoContract.Columns.TODO_TITLE
        , TodoContract.Columns.TODO_DESCRIPTION
        , TodoContract.Columns.TODO_PRIOIRITY);

        Log.d("mtodo onCreate SQL", sqlQuery);
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoContract.TABLE);
        onCreate(db);
    }

}
