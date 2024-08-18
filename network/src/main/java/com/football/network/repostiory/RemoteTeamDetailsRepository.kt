package com.football.network.repostiory

import com.football.commonModels.TeamDetailsRepository
import com.football.commonModels.model.AppTeamDetails
import com.football.network.api.TeamDetailsApi
import com.football.network.utils.toAppTeamDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteTeamDetailsRepository @Inject constructor() : TeamDetailsRepository {
    override suspend fun getCompetitionTeams(id: Long): List<AppTeamDetails> {
        val teamDetails = TeamDetailsApi.getTeamsDetails(id)

        val appTeams = arrayListOf<AppTeamDetails>()
        teamDetails.forEach { team ->
            appTeams.add(team.toAppTeamDetails())
        }
        return appTeams
    }

    override suspend fun getTeamDetails(competitionId: Long, teamId: Long): AppTeamDetails {
        TODO("Not yet implemented")
    }

    override suspend fun saveTeams(competitionDetails: List<AppTeamDetails>, competitionId: Long) {
        TODO("Not yet implemented")
    }
}