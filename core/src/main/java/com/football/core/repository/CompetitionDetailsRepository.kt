package com.football.core.repository

import com.football.core.Result
import com.football.commonModels.model.AppCompetitionDetails
import com.football.database.season.DbSeasonRepository
import com.football.network.repostiory.RemoteCompetitionDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompetitionDetailsRepository @Inject constructor(
    private val dbSeasonRepository: DbSeasonRepository,
    private val remoteCompetitionDetailsRepository: RemoteCompetitionDetailsRepository
) {
    suspend fun getCompetitionDetails(id: Long): Result<AppCompetitionDetails> {
        try {
            val result = remoteCompetitionDetailsRepository.getCompetitionDetails(id)
            dbSeasonRepository.saveCompetition(result)
            return Result.Success(result)
        } catch (t: Throwable) {
            try {
                val savedCompetitionDetails = dbSeasonRepository.getCompetitionDetails(id)
                return Result.Success(savedCompetitionDetails)

            } catch (t: Throwable) {
                return Result.Error(t)
            }
        }
    }
}