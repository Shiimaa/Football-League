package com.football.database.season

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SeasonDao {

    /**
     * Add to database a SeasonDao.
     *
     * @param season Season.
     */
    @Insert
    suspend fun insertSeason(season: Season)

    /**
     * Add to database list of Seasons.
     *
     * @param seasons List<Season>.
     */
    @Insert
    suspend fun insertSeasons(seasons: List<Season>)

    /**
     * Obtain all database added Seasons.
     *
     * @param competitionId Character identifier.
     * @return Season if exist, otherwise null.
     */
    @Query("SELECT * FROM season WHERE competitionId = :competitionId")
    suspend fun getSeason(competitionId: Long): Season?

}