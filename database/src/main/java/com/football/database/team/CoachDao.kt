package com.football.database.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoachDao {

    /**
     * Add to database a coachDao.
     *
     * @param coach Coach.
     */
    @Insert
    suspend fun insertCoach(coach: Coach)

    /**
     * Add to database list of coaches.
     *
     * @param coaches List<Coach>.
     */
    @Insert
    suspend fun insertCoaches(coaches: List<Coach>)

    /**
     * Obtain all database added coaches.
     *
     * @param teamId Character identifier.
     * @return List with coaches.
     */
    @Query("SELECT * FROM coach WHERE teamId = :teamId")
    suspend fun getCoach(teamId: Long): Coach

}