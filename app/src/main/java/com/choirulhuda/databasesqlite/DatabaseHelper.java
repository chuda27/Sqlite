package com.choirulhuda.databasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "student_database";
    private static int DATABASE_VERSION = 1;
    private String TABLE_STUDENTS = "students";
    private String STUDENT_ID = "student_id";
    private String STUDENT_FIRST_NAME = "student_first_name";
    private String CREATE_TABLE_STUDENTS = "CREATE TABLE " + TABLE_STUDENTS + "("
            + STUDENT_ID + "INTEGER PRIMARY KEY,"
            + STUDENT_FIRST_NAME + " TEXT);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("dbHelper", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS'" + TABLE_STUDENTS + "'");
        onCreate(db);
    }

    public long addStudentDetail(String studentName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_FIRST_NAME, studentName);

        //insert
        long insert = db.insert(TABLE_STUDENTS, null, values);
        return insert;
    }

    public ArrayList<String> getAllStudents(){
        ArrayList<String> studentList = new ArrayList<>();
        String name = "";
        String querySelect = "SELECT * FROM "+TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(querySelect, null);

        //mengambil semua data student
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(STUDENT_FIRST_NAME));
                studentList.add(name);
            } while (c.moveToNext());
            Log.d("Array Student", studentList.toString());
        }

        return studentList;
    }
}
