package id.showup.niken.niken_1202154297_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nikenfebriani on 25/03/18.
 */

public class TodoDbHandler extends SQLiteOpenHelper {
    private static final String TAG = TodoDbHandler.class.getSimpleName();

    //deklarasi variable yang akan digunakan seperti nama db, table, field-field
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TodoList";
    private static final String TABLE_TODO = "tbl_todo";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "desc";
    private static final String KEY_PRIORITY = "priority";

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    //constructor
    public TodoDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_PRIORITY + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    // code to add the new contact
    public void addContact(String todo, String desc, String priority) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, todo);
        values.put(KEY_DESC, desc);
        values.put(KEY_PRIORITY, priority);

        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    // code to get all contacts in a list view
    public Todo getAllToDos(int position) {
        String selectQuery = "SELECT  * FROM " + TABLE_TODO +
                " ORDER BY " + KEY_PRIORITY + " ASC " +
                "LIMIT " + position + ",1";
        Cursor cursor = null;
        Todo todo = new Todo();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            todo.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            todo.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            todo.setDesc(cursor.getString(cursor.getColumnIndex(KEY_DESC)));
            todo.setPriority(cursor.getString(cursor.getColumnIndex(KEY_PRIORITY)));

            Log.d("getTodo", "berhasil load");
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            cursor.close();
            return todo;
        }
    }

    // Deleting single contact
    public int delete(int id) {
        int deleted = 0;

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(TABLE_TODO,
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "DELETE EXCEPTION! " + e.getMessage());
        }
        return deleted;
    }

    public long count() {
        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(mReadableDB, TABLE_TODO);
    }
}
