package com.example.GymQuest.View

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.GymQuest.ApiKeys
import com.example.GymQuest.Model.FirebaseRepository
import com.example.GymQuest.Model.Player
import com.example.GymQuest.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class DietActivity : AppCompatActivity() {

    var totalCalories = 0
    var totalFat = 0
    var totalProtein = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.diet_activity)

        val promptText = findViewById<EditText>(R.id.foodText)

        findViewById<Button>(R.id.button4).setOnClickListener {
            val foodName = promptText.text.toString()
            runQuery(foodName) { response, calorie, fat, protein ->
                runOnUiThread {

                    var returnCalorie = extractFieldFromJson(response,"caloric")
                    var returnFat = extractFieldFromJson(response,"fat")
                    var returnProtein = extractFieldFromJson(response,"protein")

                    totalCalories += returnCalorie
                    totalFat += returnFat
                    totalProtein += returnProtein

                    findViewById<TextView>(R.id.elementCalorie).text = "Calorie(s): $totalCalories"
                    findViewById<TextView>(R.id.elementFat).text = "Fat: $totalFat"
                    findViewById<TextView>(R.id.elementProtein).text = "Protein: $totalProtein"

                    if (totalProtein >= 80) {
                        findViewById<TextView>(R.id.congratsProtein).visibility = View.VISIBLE
                        findViewById<TextView>(R.id.secondCongrats).visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    fun extractFieldFromJson(jsonString: String, field: String): Int {
        val jsonObject = JSONObject(jsonString)
        val dishes = jsonObject.getJSONArray("dishes")
        val firstDish = dishes.getJSONObject(0)

        if (firstDish.has(field))
            return firstDish.getInt(field)

        return 0
    }

    private fun runQuery(foodName: String, callback: (String, Int, Double, Double) -> Unit) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://dietagram.p.rapidapi.com/apiFood.php?name=$foodName&lang=en")
            .get()
            .addHeader("X-RapidAPI-Key", ApiKeys.DIET_API_KEY)
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
                    val jsonObject = JSONObject(responseData)
                    val dishes = jsonObject.getJSONArray("dishes")
                    val firstDish = dishes.getJSONObject(0)

                    val calorie = firstDish.getInt("caloric")
                    val fat = firstDish.getDouble("fat")
                    val protein = firstDish.getDouble("protein")

                    callback(responseData, calorie, fat, protein)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }
}
