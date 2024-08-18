package com.football.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.football.database.competition.Competition
import com.football.database.competition.CompetitionDao
import com.football.database.season.Season
import com.football.database.season.SeasonDao
import com.football.database.season.WinnerTeam
import com.football.database.season.WinnerTeamDao
import com.football.database.team.Coach
import com.football.database.team.CoachDao
import com.football.database.team.Player
import com.football.database.team.PlayerDao
import com.football.database.team.TeamDetails
import com.football.database.team.TeamDetailsDao

@Database(
    entities = [
        Competition::class,
        Coach::class,
        Player::class,
        TeamDetails::class,
        Season::class,
        WinnerTeam::class
    ],
    version = 1,
)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun competitionDao(): CompetitionDao
    abstract fun coachDao(): CoachDao
    abstract fun playerDao(): PlayerDao
    abstract fun teamDetailsDao(): TeamDetailsDao
    abstract fun seasonDao(): SeasonDao
    abstract fun winnerTeamDao(): WinnerTeamDao

    companion object {
        private lateinit var INSTANCE: FootballDatabase

        fun getDatabase(context: Context): FootballDatabase {
            synchronized(FootballDatabase::class.java) {
                if (!(::INSTANCE.isInitialized)) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, FootballDatabase::class.java, "football"
                    ).build()
                }
            }
            return INSTANCE
        }

    }
}


