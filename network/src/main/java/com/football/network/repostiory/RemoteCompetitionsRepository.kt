package com.football.network.repostiory

import com.football.commonModels.CompetitionsRepository
import com.football.commonModels.model.AppCompetition
import com.football.network.api.CompetitionsApi
import com.football.network.utils.toAppCompetitions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCompetitionsRepository @Inject constructor() : CompetitionsRepository {
    override suspend fun getCompetitions(): List<AppCompetition> {
        val competitions = CompetitionsApi.getCompetitions()

        val appCompetitions = arrayListOf<AppCompetition>()
        competitions.forEach { competition ->
            appCompetitions.add(competition.toAppCompetitions())
        }

        return appCompetitions
    }

    override suspend fun saveCompetitions(competitions: List<AppCompetition>) {
        TODO("Not yet implemented")
    }
}