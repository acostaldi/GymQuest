package com.example.GymQuest.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.GymQuest.R

class ClaudeTest2 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Create instance of ClaudeTest2
        val claudeTest2 = ClaudeTest3()

        // Start chat
        claudeTest2.chat()
    }
}