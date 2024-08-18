package com.football.database.competition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CompetitionDao {

    /**
     * Add to database a CompetitionDao.
     *
     * @param competition Competition.
     */
    @Insert
    suspend fun insertCompetition(competition: Competition)

    /**
     * Add to database list of Competitions.
     *
     * @param competitions List<Competition>.
     */
    @Insert
    suspend fun insertCompetitions(competitions: List<Competition>)

    /**
     * Obtain all database added Competitions.
     *
     * @return List with Competitions.
     */
    @Query("SELECT * FROM competition ")
    suspend fun getCompetitions(): List<Competition>

    /**
     * Obtain all database added Competitions.
     *
     * @return Competition if exist, otherwise null.
     */
    @Query("SELECT * FROM competition WHERE id= :competitionId ")
    suspend fun getCompetition(competitionId: Long): Competition?

}