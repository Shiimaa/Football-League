package com.football.core.repository

import com.football.commonModels.model.AppCompetitionTeam
import com.football.commonModels.model.AppTeamDetails
import com.football.core.Result
import com.football.core.utils.toAppCompetitionTeam
import com.football.database.team.DbTeamsRepository
import com.football.network.repostiory.RemoteTeamDetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamDetailsRepository @Inject constructor(
    private val dbTeamsRepository: DbTeamsRepository,
    private val remoteTeamDetailsRepository: RemoteTeamDetailsRepository
) {
    suspend fun getCompetitionTeams(id: Long): Result<List<AppCompetitionTeam>> {
        try {
            val result = remoteTeamDetailsRepository.getCompetitionTeams(id)
            dbTeamsRepository.saveTeams(result, id)

            val appCompetitionTeam = arrayListOf<AppCompetitionTeam>()
            result.forEach { team ->
                appCompetitionTeam.add(team.toAppCompetitionTeam())
            }

            return Result.Success(appCompetitionTeam)
        } catch (t: Throwable) {
            try {
                val result = dbTeamsRepository.getCompetitionTeams(id)
                return Result.Success(result)
            } catch (t: Throwable) {
                return Result.Error(t)
            }
        }

    }

    suspend fun getTeamDetails(competitionId: Long, teamId: Long): Result<AppTeamDetails> {
        try {
            val result = dbTeamsRepository.getTeamDetails(competitionId, teamId)

            return Result.Success(result)
        } catch (t: Throwable) {
            return Result.Error(t)
        }
    }
}