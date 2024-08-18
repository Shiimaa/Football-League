package com.football.network.responses

import com.google.gson.annotations.SerializedName

data class CompetitionsResponse(
    @SerializedName("competitions") val competitions: List<Competition>
)

data class Competition(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("emblem") val logo: String
)
