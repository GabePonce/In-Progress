package com.MOBI3002.in_progress.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "InProgress.db"
        const val DATABASE_VERSION = 1;
        //users table
        const val USERS_TABLE = "Users";
            //users table column info
            const val USER_ID = "user_id"; //int, auto increment, primary key
            const val USER_EMAIL = "email"; //text, not null
            const val USER_PASSWORD = "password"; //text, not null
            const val USER_NAME = "name"; //text, not null
        const val TASKS_TABLE = "Tasks";
            //tasks table column info
            const val TASKS_ID = "task_id"; //int, auto increment, primary key
            const val TASK_DESC = "description"; //text, not null
            const val TASK_DATE = "due_date"; //text, not null;
    }

    override fun onCreate(db: SQLiteDatabase) {
        //script to create the users tables
        val sql = "CREATE TABLE $USERS_TABLE ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_EMAIL TEXT NOT NULL, $USER_NAME TEXT NOT NULL, $USER_PASSWORD TEXT NOT NULL);"
        db.execSQL(sql);
        //script to create the tasks table
        val sql2 = "CREATE TABLE $TASKS_TABLE ($TASKS_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_ID INTEGER NOT NULL, $TASK_DESC TEXT NOT NULL, $TASK_DATE TEXT, FOREIGN KEY ($USER_ID) REFERENCES $USERS_TABLE($USER_ID) ON DELETE CASCADE);"
        db.execSQL(sql2);
    }

    override fun onUpgrade(db: SQLiteDatabase, v1: Int, v2: Int){
        db.execSQL("DROP TABLE $TASKS_TABLE")
        db.execSQL("DROP TABLE $USERS_TABLE")
        onCreate(db)
    }

    fun insertUser(email: String, name: String, pass: String): Boolean{
        val db = this.writableDatabase;
        val values = ContentValues().apply {
            put(USER_EMAIL, email)
            put(USER_NAME, name)
            put(USER_PASSWORD, pass)
        }
        val result = db.insert(USERS_TABLE, null, values)
        db.close()
        return result != -1L
    }

    fun retrieveUser(email: String, pass: String){
        val db = this.readableDatabase

    }
}