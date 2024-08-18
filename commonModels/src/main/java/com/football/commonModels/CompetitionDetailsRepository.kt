package com.football.commonModels

import com.football.commonModels.model.AppCompetitionDetails

interface CompetitionDetailsRepository {
    suspend fun getCompetitionDetails(id: Long): AppCompetitionDetails

    suspend fun saveCompetition(competitionDetails: AppCompetitionDetails)
}