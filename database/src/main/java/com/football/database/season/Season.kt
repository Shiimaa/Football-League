package com.football.database.season

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "season")
data class Season(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo("startDate") val startDate: String,
    @ColumnInfo("endDate") val endDate: String,
    @ColumnInfo("competitionId") val competitionId: Long
)
