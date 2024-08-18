package com.football.network.responses

import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    @SerializedName("teams") val teams: List<TeamDetails>
)

data class TeamDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("shortName") val shortName: String,
    @SerializedName("crest") val logo: String,
    @SerializedName("founded") val founded: String?,
    @SerializedName("venue") val venue: String?,
    @SerializedName("coach") val coach: Coach,
    @SerializedName("squad") val players: List<PlayerDetails>
)

data class Coach(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?,
    @SerializedName("nationality") val nationality: String?
)

data class PlayerDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("position") val position: String?,
    @SerializedName("nationality") val nationality: String?
)
