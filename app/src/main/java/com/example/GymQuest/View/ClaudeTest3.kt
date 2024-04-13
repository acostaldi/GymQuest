package com.example.GymQuest.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.GymQuest.Model.ClaudeRepository
import com.example.GymQuest.Model.KlaudeMessage
import com.example.GymQuest.Model.KlaudeMessageType
import com.example.GymQuest.R

class ClaudeTest3 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val claudeRepository = ClaudeRepository.Builder()
            .key("CLAUDE_API_KEY")
            .model("claude-3")
            .maxTokensToSample(500)
            .build()

        val editText = findViewById<EditText>(R.id.editText)
        val chatArea = findViewById<TextView>(R.id.textView)

        val responseHandler: (String?) -> Unit = { response ->
            runOnUiThread {
                if (!response.isNullOrBlank()) {
                    chatArea.append("Assistant: $response\n")
                } else {
                    chatArea.append("Assistant: No response\n")
                }
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val userInput = editText.text.toString().trim()
            if (userInput.isNotBlank()) {
                val message = KlaudeMessage(userInput, KlaudeMessageType.USER)
                val messages = mutableListOf(message)
                claudeRepository.chat(messages) { response ->
                    responseHandler(response)
                }
                chatArea.append("You: $userInput\n")
                editText.text.clear()
            }
        }
    }
}