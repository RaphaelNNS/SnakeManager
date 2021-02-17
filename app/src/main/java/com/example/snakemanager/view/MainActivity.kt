package com.example.snakemanager.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.snakemanager.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Hide action bar
        supportActionBar?.hide()

        //Buttons functions
        val button_1 = findViewById<Button>(R.id.button_main_1)
        button_1.setOnClickListener {
            val screen = Intent(this, ListActivity::class.java)
            startActivity(screen)
        }

        val button_2 = findViewById<Button>(R.id.button_main_2)
        button_2.setOnClickListener {
            val screen = Intent(this, InsertActivity::class.java)
            startActivity(screen)
        }

        val button_3 = findViewById<Button>(R.id.button_main_3)
        button_3.setOnClickListener {
            val screen = Intent(this, AnalyzeActivity::class.java)
            startActivity(screen)
        }
    }
}