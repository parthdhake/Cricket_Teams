package com.qourall.pplcricket.data.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.qourall.pplcricket.data.models.Team

@Dao
interface TeamDao {

    @Insert
    suspend fun insertTeamDetails(team: Team)

    @Query("SELECT * from teams")
    suspend fun getTeams() : List<Team>


}