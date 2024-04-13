package com.example.GymQuest.View

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.GymQuest.R

class ClaudeTest2 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Create instance of ClaudeTest2
        val claudeTest2 = ClaudeTest2()

        // TODO: IMPLEMENT AND FIX THIS
        // Create a response handler
        val responseHandler: (Any) -> Unit = { response ->
            println(response)
            println("Hello")
        }

        val editText = findViewById<EditText>(R.id.editText)
        val currentText = editText.text.toString()
        println(currentText)

        // Start chat
        // claudeTest2.chat()
    }
}