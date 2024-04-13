package com.example.GymQuest.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.GymQuest.Model.FirebaseRepository
import com.example.GymQuest.R

class CreatePlayer : AppCompatActivity(){

    private lateinit var firebaseRepository: FirebaseRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.create_player_activity)

        firebaseRepository = FirebaseRepository()

        val editText1 = findViewById<EditText>(R.id.editText)

        val createNewPlayer = findViewById<Button>(R.id.button)

        createNewPlayer.setOnClickListener {
            // Get the player name from the EditText
            val playerName = editText1.text.toString()

            // Add the player name to the database
            firebaseRepository.addPlayer(playerName)

            // After adding the player name to the database, start the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
