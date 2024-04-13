package com.example.GymQuest.View

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import com.example.GymQuest.R

class ClaudeTest4 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val chatArea = findViewById<TextView>(R.id.textView)

        val apiKey = ApiKeys.API_KEY
        val apiUrl = "https://api.anthropic.com/v1/messages"

        val headers = mapOf(
            Headers.CONTENT_TYPE to "application/json",
            "x-api-key" to apiKey,
            "anthropic-version" to "2023-06-01"
        )

        val data = mapOf(
            "model" to "claude-3-opus-20240229",
            "max_tokens" to 1024,
            "messages" to listOf(
                mapOf(
                    "role" to "user",
                    "content" to "How many moons does Jupiter have?"
                )
            )
        )

        findViewById<Button>(R.id.button2).setOnClickListener {
            Fuel.post(apiUrl)
                .header(headers)
                .jsonBody(data.toString())
                .responseString { _, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            chatArea.text = "Error: ${response.statusCode} ${ex.message}"
                        }
                        is Result.Success -> {
                            chatArea.text = "Response: ${result.get()}"
                        }
                    }
                }
        }
    }
}