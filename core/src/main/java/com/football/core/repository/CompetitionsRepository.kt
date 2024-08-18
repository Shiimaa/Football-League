package com.football.core.repository

import com.football.core.Result
import com.football.commonModels.model.AppCompetition
import com.football.database.competition.DbCompetitionsRepository
import com.football.network.repostiory.RemoteCompetitionsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompetitionsRepository @Inject constructor(
    private val dbCompetitionsRepository: DbCompetitionsRepository,
    private val remoteCompetitionsRepository: RemoteCompetitionsRepository
) {
    suspend fun getCompetitions(): Result<List<AppCompetition>> {
        try {
            val result = remoteCompetitionsRepository.getCompetitions()
            dbCompetitionsRepository.saveCompetitions(result)
            return Result.Success(result)
        } catch (t: Throwable) {
            val savedCompetitions = dbCompetitionsRepository.getCompetitions()
            if (savedCompetitions.isNotEmpty())
                return Result.Success(savedCompetitions)

            return Result.Error(t)
        }

    }
}