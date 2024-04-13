package com.example.GymQuest.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.GymQuest.Model.FirebaseRepository
import com.example.GymQuest.Model.User

class MainActivityViewModel: ViewModel() {
    val TAG = "FIRESTORE_VIEW_MODEL"
    var firebaseRepository = FirebaseRepository()
    var savedUsers : MutableLiveData<List<User>> = MutableLiveData()

    // save address to firebase
    fun saveAddressToFirebase(user: User){
        firebaseRepository.saveUserInfo(user)
    }
}