package com.example.GymQuest.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.GymQuest.Adapter.QuestAdapter
import com.example.GymQuest.Model.FirebaseRepository
import com.example.GymQuest.R

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var questAdapter: QuestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        questAdapter = QuestAdapter(emptyList())
        recyclerView.adapter = questAdapter

        fetchQuests()
    }

    private fun fetchQuests() {
        val firebaseRepo = FirebaseRepository()
        firebaseRepo.getAllQuests(
            onSuccess = { quests ->
                runOnUiThread {
                    questAdapter = QuestAdapter(quests)
                    recyclerView.adapter = questAdapter
                }
            },
            onFailure = { exception ->
                // Handle failure
            }
        )
    }
}
