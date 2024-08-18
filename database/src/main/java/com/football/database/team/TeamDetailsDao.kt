package com.football.database.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TeamDetailsDao {

    /**
     * Add to database a TeamDetailsDao.
     *
     * @param teamDetails TeamDetails.
     */
    @Insert
    suspend fun insertTeam(teamDetails: TeamDetails)


    /**
     * Obtain all database added coaches.
     *
     * @param teamId Character identifier.
     * @param competitionId Character identifier.
     * @return TeamDetails if exist, otherwise null.
     */
    @Query("SELECT * FROM teamDetails WHERE id = :teamId AND competitionId= :competitionId")
    suspend fun getTeamDetails(teamId: Long, competitionId: Long): TeamDetails?

    /**
     * Obtain all database added coaches.
     *
     * @param competitionId Character identifier.
     * @return Teams for a competition.
     */
    @Query("SELECT * FROM teamDetails WHERE competitionId = :competitionId")
    suspend fun getCompetitionTeams(competitionId: Long): List<TeamDetails>

}