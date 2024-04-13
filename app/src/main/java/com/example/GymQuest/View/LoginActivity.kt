//package com.example.GymQuest.View
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.GymQuest.R
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
//
//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login_activity)
//
//        val login = findViewById<Button>(R.id.loginButton)
//
//        login.setOnClickListener {
//            val intentPatient = Intent(this, MainActivity::class.java)
//            startActivity(intentPatient)
//        }
//
//        login.setOnClickListener {
//            val user = username.text.toString()
//            val password = password.txt.toString()
//
//            if (user.isNotEmpty() && pass.isNotEmpty()) {
//                firebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener { task ->
//                    //Direct to correct homepage based on boolean isCaregiverMode and successful log in
//                    if (task.isSuccessful) {
//                        if(isCaregiverMode){
//                            //Direct to caregiver homepage
//                            val caregiverHome = Intent(this, CaregiverHomeActivity::class.java)
//                            startActivity(caregiverHome)
//                        }
//                        else{
//                            //Treating main activity as our patient homepage for the time being
//                            //Need to change to direct to Patient Homepage after testing
//                            val patientHome = Intent(this, MainActivity::class.java)
//                            startActivity(patientHome)
//                        }
//                    } else {
//                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//        }
//
//        // Initialize Firebase Auth
//        auth = FirebaseAuth.getInstance()
//
//    }
//
//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//    }
//
//    auth.createUserWithEmailAndPassword(email, password)
//    .addOnCompleteListener(this) { task ->
//        if (task.isSuccessful) {
//            // Sign in success, update UI with the signed-in user's information
//            Log.d(TAG, "createUserWithEmail:success")
//            val user = auth.currentUser
//            updateUI(user)
//        } else {
//            // If sign in fails, display a message to the user.
//            Log.w(TAG, "createUserWithEmail:failure", task.exception)
//            Toast.makeText(
//                baseContext,
//                "Authentication failed.",
//                Toast.LENGTH_SHORT,
//            ).show()
//            updateUI(null)
//        }
//    }
//
//
//}
