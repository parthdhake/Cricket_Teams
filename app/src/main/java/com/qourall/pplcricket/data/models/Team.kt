package com.qourall.pplcricket.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey
    val team_name: String,
    val city: String,
    val team_coach: String,
    val team_owner: String
)