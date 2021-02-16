package com.example.snakemanager.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.snakemanager.R
import com.example.snakemanager.db.DatabaseHandler
import com.example.snakemanager.model.Snake

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val databaseHandler = DatabaseHandler(this)

        val name_input = findViewById<EditText>(R.id.input_insert_1)
        val length_input = findViewById<EditText>(R.id.input_insert_2)
        val color_input = findViewById<EditText>(R.id.input_insert_3)
        val button_1 = findViewById<Button>(R.id.button_insert_1)
        val callbacktxt = findViewById<TextView>(R.id.callbacktxt)


        //Button function
        button_1.setOnClickListener {
            Toast.makeText(this, "button as clicked", Toast.LENGTH_SHORT).show()

            if(name_input.text.isEmpty()){
                callbacktxt.text = "Você se esqueceu de dar um nome à sua cobra"
                callbacktxt.setTextColor(Color.parseColor("#FFBB86FC"))
            }else{
                val snake = Snake(
                        name = name_input.text.toString(),
                        lenght = length_input.text.toString().toInt(),
                        color = color_input.text.toString()
                )
                //cleaning the edittext
                name_input.text = null
                length_input.text = null
                color_input.text = null
                //adding the snake
                databaseHandler.addSnake(snake)
                //callback
                callbacktxt.text = "nome inserido"
                callbacktxt.setTextColor(Color.parseColor("#FFBB86FC"))


            }
        }
    }
}