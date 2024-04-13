package com.example.GymQuest.Model

data class Quest(
    var questName: String = "",
    var questDesc: String = "",

    @field:JvmField
    var isCompleted: Boolean = false
)