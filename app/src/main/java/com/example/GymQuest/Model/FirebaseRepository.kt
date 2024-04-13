package com.example.GymQuest.Model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()
    private val patientsCollection = db.collection("users")
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun addPlayer(name: String) {
        val patientDocRef = patientsCollection.document(name)

        val patientData = hashMapOf(
            "name" to name
        )

        patientDocRef.set(patientData)
            .addOnSuccessListener {
                println("Patient document created/updated in Firestore for user: $name")
            }
            .addOnFailureListener { e ->
                println("Error creating/updating patient document in Firestore: $e")
            }
    }


}