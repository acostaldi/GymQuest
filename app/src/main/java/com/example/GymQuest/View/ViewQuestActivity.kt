package com.example.GymQuest.View

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.GymQuest.Adapter.QuestAdapter
import com.example.GymQuest.R

class ViewQuestActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var questAdapter: QuestAdapter
    private lateinit var questName: String // Add questName parameter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.view_quest_activity)

        // Retrieve the questName parameter from the intent
        questName = intent.getStringExtra("questName") ?: ""

        // Use questName as needed in your activity
        // For example, you can display it in a TextView
        val questNameTextView = findViewById<TextView>(R.id.editQuestName)
        questNameTextView.text = questName
    }
}
