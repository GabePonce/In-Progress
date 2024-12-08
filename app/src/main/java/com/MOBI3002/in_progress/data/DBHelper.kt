package com.MOBI3002.in_progress.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.MOBI3002.in_progress.classes.Task
import com.MOBI3002.in_progress.classes.Users

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "InProgress.db"
        const val DATABASE_VERSION = 1
        //users table
        const val USERS_TABLE = "Users"
            //users table column info
            const val USER_ID = "user_id" //int, auto increment, primary key
            const val USER_EMAIL = "email" //text, not null
            const val USER_PASSWORD = "password" //text, not null
            const val USER_NAME = "name" //text, not null
        const val TASKS_TABLE = "Tasks"
            //tasks table column info
            const val TASK_ID = "task_id" //int, auto increment, primary key
            const val TASK_DESC = "description" //text, not null
            const val TASK_DATE = "due_date" //text, not null;
    }

    override fun onCreate(db: SQLiteDatabase) {
        //script to create the users tables
        val sql = "CREATE TABLE $USERS_TABLE ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_EMAIL TEXT NOT NULL UNIQUE, $USER_NAME TEXT NOT NULL, $USER_PASSWORD TEXT NOT NULL);"
        db.execSQL(sql)
        //script to create the tasks table
        val sql2 = "CREATE TABLE $TASKS_TABLE ($TASK_ID INTEGER PRIMARY KEY AUTOINCREMENT, $USER_ID INTEGER NOT NULL, $TASK_DESC TEXT NOT NULL, $TASK_DATE TEXT, FOREIGN KEY ($USER_ID) REFERENCES $USERS_TABLE($USER_ID) ON DELETE CASCADE);"
        db.execSQL(sql2)
    }

    //function to drop and update the table schema if necessary
    override fun onUpgrade(db: SQLiteDatabase, v1: Int, v2: Int){
        db.execSQL("DROP TABLE $TASKS_TABLE")
        db.execSQL("DROP TABLE $USERS_TABLE")
        onCreate(db)
    }

    //function to insert a user into the database
    //used after authenticating
    fun insertUser(email: String, name: String, pass: String): Boolean{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(USER_EMAIL, email)
            put(USER_NAME, name)
            put(USER_PASSWORD, pass)
        }
        val result = db.insert(USERS_TABLE, null, values)
        db.close()
        return result != -1L
    }

    //used for creating a user object to utilize the user Id mainly
    fun retrieveUser(email: String, pass: String):Users?{
        val db = this.readableDatabase
            //retrieves where the username and password match nad since user email
            //is unique it will only ever find one if one exists
            val cursor = db.query(
                USERS_TABLE,
                arrayOf(USER_ID, USER_NAME, USER_EMAIL),
                "$USER_EMAIL=? AND $USER_PASSWORD=?",
                arrayOf(email, pass),
                null,
                null,
                null,
                null
            )
            if (cursor.moveToFirst()) {
                val user = Users(
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL))
                )
                cursor.close()
                db.close()
                return user
            }
        //can be used for authentication because if something isn't found it returns null
        db.close()
        return null
    }

    //used to insert a newly created task, dueDate can be null to allow for tasks
    // that don't have a due date
    fun insertTask(description: String, dueDate: String?, uId: Int): Boolean{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(USER_ID, uId)
            put(TASK_DESC, description)
            put(TASK_DATE, dueDate)
        }
        val result = db.insert(TASKS_TABLE, null, values)
        db.close()
        //returns true if successfully inserts new task to the database
        return result != -1L
    }

    //retrieves all of one users tasks, used in the Tasks composable to display all the tasks
    fun getTasks(uId: Int): List<Task>{
        val db = this.readableDatabase
        val cursor = db.query(TASKS_TABLE, null, "$USER_ID=?", arrayOf(uId.toString()), null, null, null, null)
        val tasks = mutableListOf<Task>()
        if (cursor != null && cursor.moveToFirst()){
            do {
                tasks.add(
                    Task(
                        cursor.getInt(cursor.getColumnIndexOrThrow(TASK_ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASK_DESC)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASK_DATE))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return tasks
    }


    //get the tasks that specfically have a due date, used in the calendar section
    fun getDateTasks(uId: Int): List<Task>{
        val db = this.readableDatabase
        val cursor = db.query(TASKS_TABLE, null, "$USER_ID=? AND $TASK_DATE IS NOT NULL", arrayOf(uId.toString()), null, null, null, null)
        val tasks = mutableListOf<Task>()
        if (cursor != null && cursor.moveToFirst()){
            do {
                tasks.add(
                    Task(
                        cursor.getInt(cursor.getColumnIndexOrThrow(TASK_ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASK_DESC)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TASK_DATE)) ?: null
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return tasks
    }

    //removes a task based on taskID, used when "completing a task"
    fun deleteTask(tId: Int):Boolean{
        val db = this.writableDatabase
        val result = db.delete(
            TASKS_TABLE,
            "$TASK_ID = ?",
            arrayOf(tId.toString())
        )

        db.close()

        return result > 0
    }
}