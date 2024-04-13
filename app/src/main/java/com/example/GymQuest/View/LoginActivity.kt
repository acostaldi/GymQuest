package com.example.GymQuest.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.GymQuest.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val login = findViewById<Button>(R.id.loginButton)

        login.setOnClickListener {
            val intent = Intent(this, CreatePlayer::class.java)
            startActivity(intent)
        }

        val claude = findViewById<Button>(R.id.button3)

        claude.setOnClickListener {
            val intent = Intent(this, ClaudeTest3::class.java)
            startActivity(intent)
        }

    }
}
