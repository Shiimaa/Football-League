package com.football.commonModels

import com.football.commonModels.model.AppTeamDetails
import com.football.commonModels.model.BaseTemData

interface TeamDetailsRepository {
    suspend fun getCompetitionTeams(id: Long): List<BaseTemData>

    suspend fun getTeamDetails(competitionId: Long, teamId: Long): AppTeamDetails

    suspend fun saveTeams(competitionDetails: List<AppTeamDetails>, competitionId: Long)
}