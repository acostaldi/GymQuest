package com.example.GymQuest.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.GymQuest.Model.FirebaseRepository
import com.example.GymQuest.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseRepository: FirebaseRepository
    private val db = FirebaseFirestore.getInstance()


    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            handleSignInResult(data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        firebaseAuth = FirebaseAuth.getInstance()

        val login = findViewById<Button>(R.id.loginButton)
        firebaseRepository = FirebaseRepository()
        login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            signInWithGoogle()
        }

        val food = findViewById<Button>(R.id.button5)

        food.setOnClickListener {
            val intent = Intent(this, DietActivity::class.java)
            startActivity(intent)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }


    private fun handleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign-In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!) { success, userId ->
                if (success) {
                    // TODO: Make check so that if user already exists, they are taken to MainActivity
                    val intent = Intent(this, CreatePlayer::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Handle authentication failure
                }
            }
        } catch (e: ApiException) {
            // Google Sign-In failed, update UI accordingly
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String, onComplete: (Boolean, String?) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val userId = firebaseAuth.currentUser?.uid
                    val email = user?.email

                    if (userId != null && email != null) {
                        // Create or update the user document in the "users" collection
                        val userDocRef = db.collection("users").document(userId)

                        val userData = hashMapOf(
                            "email" to email,
                        )

                        userDocRef.set(userData)
                            .addOnSuccessListener {
                                println("User document created/updated in Firestore for user: $userId")
                            }
                            .addOnFailureListener { e ->
                                println("Error creating/updating user document in Firestore: $e")
                            }
                    }


                    onComplete.invoke(true, userId)
                } else {
                    onComplete.invoke(false, null)
                }
            }
    }
}
