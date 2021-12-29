package com.qourall.pplcricket.data.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qourall.pplcricket.data.models.Team
import com.qourall.pplcricket.data.models.TeamMember

@Database(entities = [Team::class, TeamMember::class], version = 3, exportSchema = false)
abstract class TeamDatabase : RoomDatabase(){

    abstract fun teamDao() : TeamDao
    abstract fun memberDao() : MemberDao

    companion object{
        @Volatile
        private var INSTANCE : TeamDatabase? = null

        fun getDatabase(context: Context) : TeamDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TeamDatabase::class.java,
                    "team_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}