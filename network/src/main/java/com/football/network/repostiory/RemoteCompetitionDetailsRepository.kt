package com.football.network.repostiory

import com.football.commonModels.CompetitionDetailsRepository
import com.football.commonModels.model.AppCompetitionDetails
import com.football.network.api.CompetitionDetailsApi
import com.football.network.utils.toAppCompetitionDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCompetitionDetailsRepository @Inject constructor() : CompetitionDetailsRepository {
    override suspend fun getCompetitionDetails(id: Long): AppCompetitionDetails {
        val competitionDetails = CompetitionDetailsApi.getCompetitionDetails(id)

        return competitionDetails.toAppCompetitionDetails()
    }

    override suspend fun saveCompetition(competitionDetails: AppCompetitionDetails) {
        TODO("Not yet implemented")
    }
}