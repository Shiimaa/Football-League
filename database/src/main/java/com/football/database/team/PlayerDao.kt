package com.football.database.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDao {

    /**
     * Add to database a PlayerDao.
     *
     * @param player Player.
     */
    @Insert
    suspend fun insertPlayer(player: Player)

    /**
     * Add to database list of Players.
     *
     * @param players List<Player>.
     */
    @Insert
    suspend fun insertPlayers(players: List<Player>)

    /**
     * Obtain all database added Players.
     *
     * @param teamId Character identifier.
     * @param competitionId Character identifier.
     * @return List with Players.
     */
    @Query("SELECT * FROM player WHERE teamId = :teamId AND competitionId= :competitionId")
    suspend fun getPlayers(teamId: Long,competitionId:Long): List<Player>

}