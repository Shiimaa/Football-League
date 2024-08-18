package com.football.database.season

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "winnerTeam")
data class WinnerTeam(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo("crest") val logo: String,
    @ColumnInfo(name = "seasonId") val seasonId: Long,
    @ColumnInfo(name = "competitionId") val competitionId: Long
)
