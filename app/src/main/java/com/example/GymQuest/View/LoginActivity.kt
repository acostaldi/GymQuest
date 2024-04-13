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


    private var signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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
            signInWithGoogle()
        }

        val claude = findViewById<Button>(R.id.button3)

        claude.setOnClickListener {
            val intent = Intent(this, ClaudeAPI::class.java)
            startActivity(intent)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                handleSignInResult(data)
            }
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }



//    signInRequest = BeginSignInRequest.builder()
//        .setGoogleIdTokenRequestOptions(
//            BeginSignInRequest.GoogleIdTokenRequestOptions.builder.setSupported(true)
//            // Your server's client ID, not your Android client ID.
//            .setServerClientId(getString(R.string.your_web_client_id)
//    )
//    // Only show accounts previously used to sign in.
//    .setFilterByAuthorizedAccounts(true)
//    .build())

    private fun handleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign-In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!) { success, userId ->
                if (success) {
                    val userDocRef = db.collection("users").document(userId!!)
                    userDocRef.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("email", account.email)
                                this@LoginActivity.startActivity(intent)
                                finish()
                            }
                        }
                        .addOnFailureListener { exception ->
                            println("Error getting user document: $exception")
                        }

                    // Saves the email as the username
                    val userData = hashMapOf(
                        "email" to account.email, // Save the email into the name field
                    )

                    userDocRef.set(userData)
                        .addOnSuccessListener {
                            println("User document created/updated in Firestore for user: $userId")
                        }
                        .addOnFailureListener { e ->
                            println("Error creating/updating user document in Firestore: $e")
                        }
                    val intent = Intent(this, MainActivity::class.java)
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
