package com.football.database.season

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WinnerTeamDao {

    /**
     * Add to database a WinnerTeam.
     *
     * @param winnerTeam WinnerTeam.
     */
    @Insert
    suspend fun insertWinnerTeam(winnerTeam: WinnerTeam)

    /**
     * Obtain all database added Seasons.
     *
     * @param seasonId Character identifier.
     * @param competitionId Character identifier.
     * @return WinnerTeam if exist, otherwise null.
     */
    @Query("SELECT * FROM winnerTeam WHERE seasonId = :seasonId AND competitionId = :competitionId")
    suspend fun getWinnerTeam(seasonId: Long, competitionId: Long): WinnerTeam?

}