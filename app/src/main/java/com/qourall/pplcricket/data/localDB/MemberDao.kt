package com.qourall.pplcricket.data.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.qourall.pplcricket.data.models.TeamMember

@Dao
interface MemberDao {

    @Insert
    suspend fun insertMemberDetails(teamMember: TeamMember)

    @Query("SELECT * from members where team_name = :team_name")
    suspend fun getTeamMembers(team_name: String) : List<TeamMember>

}