package com.example.GymQuest.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.GymQuest.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class DietActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.diet_activity)

        val chatArea = findViewById<TextView>(R.id.elementProtein)

        val promptText = findViewById<EditText>(R.id.foodText)

        findViewById<Button>(R.id.button4).setOnClickListener {
            val foodName = promptText.text.toString()
            runQuery(foodName) { response, calorie, fat, protein ->
                runOnUiThread {
                    chatArea.text = response
                    findViewById<TextView>(R.id.elementCalorie).text = "Calorie: $calorie"
                    findViewById<TextView>(R.id.elementFat).text = "Fat: $fat"
                    findViewById<TextView>(R.id.elementProtein).text = "Protein: $protein"
                }
            }
        }
    }

    private fun runQuery(foodName: String, callback: (String, Int, Double, Double) -> Unit) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://dietagram.p.rapidapi.com/apiFood.php?name=$foodName&lang=en")
            .get()
            .addHeader("X-RapidAPI-Key", "")
            .addHeader("X-RapidAPI-Host", "dietagram.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string() ?: ""

                try {
                    val jsonArray = JSONArray(responseData)
                    val jsonObject = jsonArray.getJSONObject(0) // Assuming only one dish is returned
                    val calorie = jsonObject.getInt("caloric")
                    val fat = jsonObject.getDouble("fat")
                    val protein = jsonObject.getDouble("protein")

                    callback(responseData, calorie, fat, protein)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }
}
