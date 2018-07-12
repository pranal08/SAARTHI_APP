package com.pranalspk.saarthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pranal on 03-04-2018.
 */

public class DBHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1   ;
    private static final String DATABASE_NAME = "Saarthi";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "name";
    private static final String KEY_PASS = "password";
    private static final String KEY_AGE = "age";
    private static final String KEY_SOSNO = "sosno";

    private String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USERNAME + " TEXT,"
            + KEY_PASS + " TEXT,"
            + KEY_AGE + " TEXT,"
            + KEY_SOSNO + " TEXT"
            + ")";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASS, user.getPass());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_SOSNO, user.getSoscontact());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }

    public String searchPass(String uname) {
        String name, pass;
        pass = "not found";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select " + KEY_USERNAME + " , " + KEY_PASS + " from " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);

                if (name.equalsIgnoreCase(uname)) {
                    pass = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pass;
    }

    public String get_sosno(String uname)
    {
        String name, sosno;
        sosno="7600447665";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select " + KEY_USERNAME + " , " + KEY_SOSNO + " from " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);

                if (name.equalsIgnoreCase(uname)) {
                    sosno = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return sosno;
    }

    public String displayAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        String displayData = "";
        if (cursor.moveToFirst()) {
            do {
                displayData += cursor.getString(1);
                displayData += " : " + cursor.getString(2) + "\n";
            } while (cursor.moveToNext());
        }
        cursor.close();
        return displayData;
    }
}

