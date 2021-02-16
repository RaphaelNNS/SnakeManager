package com.example.snakemanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.snakemanager.R
import com.example.snakemanager.db.DatabaseHandler
import com.example.snakemanager.model.Snake

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var databaseHandler = DatabaseHandler(this)
        var list = findViewById<TextView>(R.id.list_list_activity)

        list.text =  databaseHandler.Snakes().toString()
    }


}