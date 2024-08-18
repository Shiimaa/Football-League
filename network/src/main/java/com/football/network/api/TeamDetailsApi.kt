package com.football.network.api

import com.football.network.RetrofitClient
import retrofit2.Response
import retrofit2.http.GET
import com.football.network.responses.TeamDetails
import com.football.network.responses.TeamsResponse
import retrofit2.http.Path


object TeamDetailsApi {
    private val service =
        RetrofitClient.getRetrofitInstance().create(TeamDetailsService::class.java)

    suspend fun getTeamsDetails(competitionId: Long): List<TeamDetails> {
        val response = service.getTeamDetails(competitionId)
        val body = response.body()

        if (response.isSuccessful && body != null) {
            return body.teams
        } else {
            throw Exception("error code ${response.code()}")
        }

    }

}

interface TeamDetailsService {
    @GET("competitions/{competitionId}/teams")
    suspend fun getTeamDetails(@Path("competitionId") competitionId: Long): Response<TeamsResponse>
}