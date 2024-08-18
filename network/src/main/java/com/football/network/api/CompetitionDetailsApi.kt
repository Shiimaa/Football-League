package com.football.network.api

import com.football.network.RetrofitClient
import retrofit2.Response
import retrofit2.http.GET
import com.football.network.responses.CompetitionDetails
import retrofit2.http.Path


object CompetitionDetailsApi {
    private val service =
        RetrofitClient.getRetrofitInstance().create(CompetitionDetailsService::class.java)

    suspend fun getCompetitionDetails(competitionId: Long): CompetitionDetails {
        val response = service.getCompetitionDetails(competitionId)
        val body = response.body()

        if (response.isSuccessful && body != null) {
            return body
        } else {
            throw Exception("error code ${response.code()}")
        }

    }

}

interface CompetitionDetailsService {
    @GET("competitions/{competitionId}")
    suspend fun getCompetitionDetails(@Path("competitionId") competitionId: Long): Response<CompetitionDetails>
}