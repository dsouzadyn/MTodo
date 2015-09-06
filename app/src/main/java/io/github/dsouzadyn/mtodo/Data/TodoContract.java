package io.github.dsouzadyn.mtodo.Data;

import android.provider.BaseColumns;

/**
 * Created by DYLAN on 15-08-2015.
 */
public class TodoContract {
    public static final String DB_NAME = "io.github.dsouzadyn.mtodo.Data.todos";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "todos";

    public class Columns {
        public static final String TODO_TITLE = "title";
        public static final String TODO_DESCRIPTION = "description";
        public static final String TODO_PRIOIRITY = "priority";
        public static final String _ID = BaseColumns._ID;
    }

}
