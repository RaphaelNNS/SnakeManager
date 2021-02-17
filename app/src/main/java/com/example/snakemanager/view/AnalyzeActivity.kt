package com.example.snakemanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snakemanager.R

class AnalyzeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze)

        //Hide action bar
        supportActionBar?.hide()


    }
}