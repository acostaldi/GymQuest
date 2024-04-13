package com.example.GymQuest.View

import ApiKeys
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.GymQuest.R

class ClaudeAPI : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val chatArea = findViewById<TextView>(R.id.textView)

        val aiPromptText = findViewById<EditText>(R.id.claudeQuery)

        findViewById<Button>(R.id.button2).setOnClickListener {
            runQuery(aiPromptText.text.toString()) { response ->
                runOnUiThread {
                    chatArea.text = response
                    Log.d("ClaudeAPI", response)
                }
            }
        }
    }

    private fun runQuery(aiPrompt: String, callback: (String) -> Unit) {
        Thread {
            // Start the Python interpreter
            if (! Python.isStarted()) {
                Python.start(AndroidPlatform(this))
            }

            val python = Python.getInstance()
            val pythonScript = python.getModule("QueryAPI") // Replace with your script's name

            // Call a function from the script (replace "function_name" with your function's name)
            //val result = pythonScript.callAttr("query_API", ApiKeys.API_KEY, aiPrompt)
            //val result = pythonScript.callAttr("query_story_API", ApiKeys.API_KEY)
            val result = pythonScript.callAttr("query_fitness_API", ApiKeys.API_KEY)

            // Use the result (this assumes the function returns a string)
            callback(result.toString())
        }.start()
    }
}