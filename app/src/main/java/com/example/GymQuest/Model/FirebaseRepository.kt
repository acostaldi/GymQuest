package com.example.GymQuest.Model

import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ListenerRegistration
//import java.util.Random

class FirebaseRepository {

    val TAG = "FIREBASE_REPOSITORY"
    val database = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser

    fun saveUserInfo(user: User){

        val userMap = HashMap<String, Any>()
        userMap["strength"] = user.strength
        userMap["dexterity"] = user.dexterity
        userMap["stamina"] = user.stamina
        userMap["health"] = user.health

        database.collection("user_list").document("user")
            .set(userMap)
            .addOnSuccessListener{ Log.d(TAG, "DocumentSnapshot successfully written!")}
            //.addOnFailureListener{ e -> Log.w(TAG, "Error writting document", e)}
//        var documentReference = firestoreDB.collection("users").document(user!!.email.toString())
//            .collection("saved_addresses").document(addressItem.addressId)
//        return documentReference.set(addressItem)
    }

//    private fun readUserDataFromFirestore(){
//        mFirestore = FirebaseFirestore.getInstance()
//        mFireStore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
//
//
//    }

//    fun getUserDataLiveData(): MutableLiveData<User?> {
//        return userDataLiveData
//    }
//    fun updateUserData(userId: String, newName: String, newBio: String) {
//        val userDocRef = database.collection("users").document(userId)
//
//        val userData = hashMapOf(
//            "username" to newName,
//            "description" to newBio
//            // Add other fields as needed
//        )
//        userDocRef.update(userData as Map<String, Any>)
//            .addOnSuccessListener {
//                // Handle success
//            }
//            .addOnFailureListener { e ->
//                // Handle failure
//            }
//    }
//
}