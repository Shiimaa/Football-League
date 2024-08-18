package com.football.core.di

import com.football.database.competition.DbCompetitionsRepository
import com.football.database.season.DbSeasonRepository
import com.football.database.team.DbTeamsRepository
import com.football.network.repostiory.RemoteCompetitionDetailsRepository
import com.football.network.repostiory.RemoteCompetitionsRepository
import com.football.network.repostiory.RemoteTeamDetailsRepository

interface BaseComponent {
    fun getDbCompetitionsRepository(): DbCompetitionsRepository

    fun getDbSeasonRepository(): DbSeasonRepository

    fun getDbTeamsRepository(): DbTeamsRepository

    fun getRemoteCompetitionsRepository(): RemoteCompetitionsRepository

    fun getRemoteCompetitionDetailsRepository(): RemoteCompetitionDetailsRepository

    fun getRemoteTeamDetailsRepository(): RemoteTeamDetailsRepository

}