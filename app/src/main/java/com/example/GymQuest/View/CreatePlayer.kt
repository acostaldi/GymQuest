package com.example.GymQuest.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.GymQuest.Model.FirebaseRepository
import com.example.GymQuest.R
import com.google.firebase.auth.FirebaseAuth

class CreatePlayer : AppCompatActivity(){

    private lateinit var firebaseRepository: FirebaseRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.create_player_activity)

        firebaseRepository = FirebaseRepository()

        val editText1 = findViewById<EditText>(R.id.editText1)

        val createNewPlayer = findViewById<Button>(R.id.button)

        createNewPlayer.setOnClickListener {
            // Get the player name from the EditText
            val playerName = editText1.text.toString()

            val strength = 0
            val dexterity = 0
            val stamina = 0
            val health = 100

            // Add the player name to the database
            firebaseRepository.addPlayer(playerName, strength, dexterity, stamina, health)

            // After adding the player name to the database, start the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, CreatePlayer::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    private fun initializeUI() {
        //val sign_out_button = findViewById<Button>(R.id.loginButton)
        //sign_out_button.setOnClickListen {
        //    logout()
        //}
    }

    private fun logout() {
        //startActivity(LoginActivity.getLaunchIntent(this))
        //FirebaseAuth.getInstance().signOut()
    }
}
