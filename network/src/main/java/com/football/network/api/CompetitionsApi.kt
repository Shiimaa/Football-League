package com.football.network.api

import com.football.network.RetrofitClient
import com.football.network.responses.Competition
import com.football.network.responses.CompetitionsResponse
import retrofit2.Response
import retrofit2.http.GET

object CompetitionsApi {
    private val service = RetrofitClient.getRetrofitInstance().create(Competitions::class.java)

    suspend fun getCompetitions(): List<Competition> {
        val response = service.getCompetitions()
        val body = response.body()

        if (response.isSuccessful && body != null) {
            return body.competitions
        } else {
            throw Exception("error code ${response.code()}")
        }

    }
}

private interface Competitions {
    @GET("competitions")
    suspend fun getCompetitions(): Response<CompetitionsResponse>
}