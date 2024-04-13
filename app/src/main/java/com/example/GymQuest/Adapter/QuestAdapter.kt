package com.example.GymQuest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.GymQuest.Model.Quest
import com.example.GymQuest.R

class QuestAdapter(private val quests: List<Quest>) :
    RecyclerView.Adapter<QuestAdapter.QuestViewHolder>() {

    class QuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questNameTextView: TextView = itemView.findViewById(R.id.tvQuestName)
        val questDescTextView: TextView = itemView.findViewById(R.id.tvQuestDescription)
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
    }

    override fun getItemCount(): Int {
        return quests.size
    }
}
