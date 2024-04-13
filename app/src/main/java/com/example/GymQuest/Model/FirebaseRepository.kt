package com.example.GymQuest.Model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()
    private val playerCollection = db.collection("users")

    fun addPlayer( name: String, strength: Int, dexterity: Int, stamina: Int, health: Int) {
        val playerDocRef = patientsCollection.document(name)

        val patientData = hashMapOf(
            "name" to name,
            "strength" to stamina,
            "dexterity" to dexterity,
            "stamina" to stamina,
            "health" to health

        )

        playerDocRef.set(patientData)
            .addOnSuccessListener {
                println("Patient document created/updated in Firestore for user: $name")
            }
            .addOnFailureListener { e ->
                println("Error creating/updating patient document in Firestore: $e")
            }
    }
}