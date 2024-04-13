package com.example.GymQuest.Model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()
    private val playerCollection = db.collection("users")
    private val questCollection = db.collection("quests")
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun updateQuest(quest: Quest, onComplete: (Boolean) -> Unit) {
        val questDocRef = questCollection.document(quest.questName)
        questDocRef.set(quest)
            .addOnSuccessListener {
                println("Quest document updated in Firestore for quest: ${quest.questName}")
                onComplete(true)
            }
            .addOnFailureListener { e ->
                println("Error updating quest document in Firestore: $e")
                onComplete(false)
            }
    }

    fun updatePlayer(player: Player, onComplete: (Boolean) -> Unit) {
        val playerDocRef = playerCollection.document(player.name)
        val playerData = hashMapOf(
            "name" to player.name,
            "strength" to player.strength,
            "dexterity" to player.dexterity,
            "stamina" to player.stamina,
            "health" to player.health
        )
        playerDocRef.set(playerData)
            .addOnSuccessListener {
                println("Player document updated in Firestore for user: ${player.name}")
                onComplete(true)
            }
            .addOnFailureListener { e ->
                println("Error updating player document in Firestore: $e")
                onComplete(false)
            }
    }

    fun addPlayer( name: String, strength: Int, dexterity: Int, stamina: Int, health: Int) {
        val playerDocRef = playerCollection.document(name)

        val playerData = hashMapOf(
            "name" to name,
            "strength" to stamina,
            "dexterity" to dexterity,
            "stamina" to stamina,
            "health" to health
        )

        playerDocRef.set(playerData)
            .addOnSuccessListener {
                println("Player document created/updated in Firestore for user: $name")
            }
            .addOnFailureListener { e ->
                println("Error creating/updating player document in Firestore: $e")
            }
    }

    fun getPlayer(name: String, onSuccess: (List<Player>) -> Unit, onFailure: (Exception) -> Unit) {
        playerCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val quests = mutableListOf<Player>()
                for (document in querySnapshot) {
                    val player = document.toObject(Player::class.java)
                    quests.add(player)
                }
                onSuccess(quests)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
    fun addQuest (questName: String, questDesc: String, isCompleted: Boolean, points: Number, category: String) {
        val questDocRef = questCollection.document(questName)

        val questData = hashMapOf(
            "questName" to questName,
            "questDesc" to questDesc,
            "isCompleted" to isCompleted,
            "points" to points,
            "category" to category
        )

        questDocRef.set(questData)
            .addOnSuccessListener {
                println("Quest document created/updated in Firestore for quest: $questName")
            }
            .addOnFailureListener { e ->
                println("Error creating/updating quest document in Firestore: $e")
            }

    }

    fun getAllQuests(onSuccess: (List<Quest>) -> Unit, onFailure: (Exception) -> Unit) {
        questCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val quests = mutableListOf<Quest>()
                for (document in querySnapshot) {
                    val quest = document.toObject(Quest::class.java)
                    quests.add(quest)
                    Log.d("FireBaseRepo", "Quest: ${quest.questName}, isCompleted: ${quest.isCompleted}")
                }
                onSuccess(quests)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }
}