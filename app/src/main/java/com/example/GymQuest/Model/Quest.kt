package com.example.GymQuest.Model

data class Quest(
    var questName: String = "",
    var questDesc: String = "",
    var questReward: String = "HP",
    var questRewardAmount: Int = 10,

    @field:JvmField
    var isCompleted: Boolean = false
)