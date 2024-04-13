package com.example.GymQuest.Model

import android.graphics.Color.BLUE
import java.util.Scanner

class ClaudeTest2 {
    fun chat() {
        val scan = Scanner(System.`in`)
        val klaude = ClaudeRepository.Builder()
            .model("claude-3")
            .key("CLAUDE_API_KEY")
            .maxTokensToSample(500)
            .build()
        val messages = mutableListOf(KlaudeMessage("You are a helpful assistant", KlaudeMessageType.USER))
        println("Start chatting, type 'exit' or 'quit' to finish:\n")
        while (true) {
            println("Human:")
            val input = scan.nextLine().trim()
            if (input.isBlank()) {
                continue
            }
            if (input == "exit" || input == "quit") {
                break
            }
            messages.add(KlaudeMessage(input, KlaudeMessageType.USER))
            klaude.chat(messages) { reply ->
                if (!reply.isNullOrBlank()) {
                    messages.add(KlaudeMessage(reply.trim(), KlaudeMessageType.ASSISTANT))
                    println("Assistant:\n${reply.trim()}")
                }
            }
        }
        println("Done")
    }
}