package com.football.network.responses

import com.google.gson.annotations.SerializedName

data class CompetitionDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("emblem") val logo: String,
    @SerializedName("currentSeason") val currentSeason: SeasonDetails
)

data class SeasonDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("winner") val winnerTeam: WinnerTeam?

    )

data class WinnerTeam(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("crest") val logo: String
)


