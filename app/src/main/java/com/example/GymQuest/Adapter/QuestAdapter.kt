package com.example.GymQuest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.GymQuest.Model.FirebaseRepository
import com.example.GymQuest.Model.Quest
import com.example.GymQuest.R

class QuestAdapter(
    private var quests: List<Quest>,
    private val firebaseRepository: FirebaseRepository
) :
    RecyclerView.Adapter<QuestAdapter.QuestViewHolder>() {

    class QuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questNameTextView: TextView = itemView.findViewById(R.id.tvQuestName)
        val questDescTextView: TextView = itemView.findViewById(R.id.tvQuestDescription)
        val questCompletedCheckBox: CheckBox = itemView.findViewById(R.id.isCompleted)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_quests, parent, false)
        return QuestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestViewHolder, position: Int) {
        val currentQuest = quests[position]
        holder.questNameTextView.text = currentQuest.questName
        holder.questDescTextView.text = currentQuest.questDesc
        holder.questCompletedCheckBox.isChecked = currentQuest.isCompleted

        holder.questCompletedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currentQuest.isCompleted = true
                firebaseRepository.updateQuest(currentQuest) { isSuccess ->
                    if (isSuccess) {
                        // Update user stats
                        updatePlayerStats(currentQuest)
                    } else {
                        // Handle failure
                    }
                }
            }
        }
    }

    private fun updatePlayerStats(quest: Quest) {
        // Implement as per your requirements
    }

    override fun getItemCount(): Int {
        return quests.size
    }

    fun updateQuests(newQuests: List<Quest>) {
        quests = newQuests
        notifyDataSetChanged()
    }
}


