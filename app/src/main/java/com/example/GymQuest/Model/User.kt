package com.example.GymQuest.Model
//import com.google.firebase.database.PropertyName

data class User(
//    @get:PropertyName("strength") var strength: Int = 0,
//    @get:PropertyName("dexterity") var dexterity: Int = 0,
//    @get:PropertyName("stamina") var stamina: Int = 0,
//    @get:PropertyName("health") var health: Int = 0,
    var name: String = "",
    var strength: Int = 0,
    var dexterity: Int = 0,
    var stamina: Int = 0,
    var health: Int = 0
)