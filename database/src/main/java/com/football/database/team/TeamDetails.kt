package com.football.database.team

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teamDetails")
data class TeamDetails(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo("shortName") val shortName: String,
    @ColumnInfo("crest") val logo: String,
    @ColumnInfo("founded") val founded: String,
    @ColumnInfo("venue") val venue: String,
    @ColumnInfo(name = "competitionId") val competitionId: Long
)
