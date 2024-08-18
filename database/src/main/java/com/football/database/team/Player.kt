package com.football.database.team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class Player(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo("position") val position: String,
    @ColumnInfo("nationality") val nationality: String,
    @ColumnInfo("teamId") val teamId: Long,
    @ColumnInfo(name = "competitionId") val competitionId: Long
)
