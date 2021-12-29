package com.qourall.pplcricket.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class TeamMember(
    @PrimaryKey
    val name: String,
    val team_name: String,
    val role: String,
    val age: String
)