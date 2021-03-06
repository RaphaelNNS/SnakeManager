package com.example.snakemanager.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.snakemanager.model.Snake

class DatabaseHandler (context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ("+
                " $ID INTEGER PRIMARY KEY," +
                " $NAME TEXT," +
                " $LENGTH INTEGER," +
                " $COLOR TEXT);"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addSnake(snake: Snake): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, snake.name)
        values.put(LENGTH, snake.lenght)
        values.put(COLOR, snake.color)

        val _success = db.insert(TABLE_NAME,null,values)
        return (("$_success").toInt() != -1)
    }

    fun getSnake(_id: Int): Snake {
        val snake = Snake()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        snake.id = cursor.getInt(cursor.getColumnIndex(ID))
        snake.name = cursor.getString(cursor.getColumnIndex(NAME))
        cursor.close()
        return snake
    }

    fun Snakes(): ArrayList<Snake> {
        val snakeList = ArrayList<Snake>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    val snake = Snake()
                    snake.id = cursor.getInt(cursor.getColumnIndex(ID))
                    snake.name = cursor.getString(cursor.getColumnIndex(NAME))
                    snake.lenght = cursor.getInt(cursor.getColumnIndex(LENGTH))
                    snake.color = cursor.getString(cursor.getColumnIndex(COLOR))
                    snakeList.add(snake)
                }while(cursor.moveToNext())
            }
        }
        cursor.close()
        return snakeList
    }

    fun updateSnake(snake: Snake): Boolean{
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(NAME, snake.name)
        }
        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(snake.id.toString())).toLong()
        db.close()
        return ("$_success").toInt() != -1
    }

    fun deleteSnake(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        return ("$_success").toInt() != -1
    }

    fun deleteAllSnake(): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, null,null).toLong()
        db.close()
        return ("$_success").toInt() != -1
    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "SnakeApp"
        private val TABLE_NAME = "Snakes"
        private val ID = "Id"
        private val NAME = "Name"
        private val LENGTH = "Length"
        private val COLOR = "Color"
    }
}