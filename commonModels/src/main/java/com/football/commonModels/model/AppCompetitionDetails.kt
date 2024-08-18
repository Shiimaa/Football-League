package com.football.commonModels.model


data class AppCompetitionDetails(
    val id: Long,
    val name: String,
    val logo: String,
    val currentSeason: AppSeasonDetails,
)

data class AppSeasonDetails(
    val id: Long,
    val startDate: String,
    val endDate: String,
    val winnerTeam: AppWinnerTeam?,
)

data class AppWinnerTeam(
    val id: Long, val name: String, val logo: String
)


