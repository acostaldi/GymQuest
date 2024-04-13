package com.example.GymQuest.View

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.GymQuest.R

class ClaudeTest3 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val chatArea = findViewById<TextView>(R.id.textView)

        findViewById<Button>(R.id.button2).setOnClickListener {
            runPythonScript { response ->
                runOnUiThread {
                    chatArea.text = response
                }
            }
        }
    }

    private fun runPythonScript(callback: (String) -> Unit) {
        Thread {
            // Start the Python interpreter
            if (! Python.isStarted()) {
                Python.start(AndroidPlatform(this))
            }

            val python = Python.getInstance()
            val pythonScript = python.getModule("ClaudeTest") // Replace with your script's name

            // Call a function from the script (replace "function_name" with your function's name)
            val result = pythonScript.callAttr("query_API")

            // Use the result (this assumes the function returns a string)
            callback(result.toString())
        }.start()
    }
}