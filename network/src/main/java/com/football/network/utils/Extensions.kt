package com.football.network.utils

import com.football.commonModels.model.AppCoach
import com.football.commonModels.model.AppCompetition
import com.football.commonModels.model.AppCompetitionDetails
import com.football.commonModels.model.AppPlayer
import com.football.commonModels.model.AppSeasonDetails
import com.football.commonModels.model.AppTeamDetails
import com.football.commonModels.model.AppWinnerTeam
import com.football.network.responses.Competition
import com.football.network.responses.CompetitionDetails
import com.football.network.responses.TeamDetails

fun Competition.toAppCompetitions(): AppCompetition {
    return AppCompetition(id, name, logo)
}

fun CompetitionDetails.toAppCompetitionDetails(): AppCompetitionDetails {
    val currentSeason = currentSeason
    val winnerTeam = currentSeason.winnerTeam

    val appWinnerTeam = winnerTeam?.let {
        AppWinnerTeam(
            it.id, it.name, it.logo
        )
    }

    val appCurrentSeason = AppSeasonDetails(
        currentSeason.id, currentSeason.startDate, currentSeason.endDate, appWinnerTeam
    )

    return AppCompetitionDetails(id, name, logo, appCurrentSeason)
}

fun TeamDetails.toAppTeamDetails(): AppTeamDetails {
    val coach = coach
    val teamPlayers = players

    val appCoach = AppCoach(coach.id, coach.name.orEmpty(), coach.nationality.orEmpty())
    val appTeamPlayers = arrayListOf<AppPlayer>()
    teamPlayers.forEach { player ->
        val appPlayer =
            AppPlayer(player.id, player.name, player.position.orEmpty(), player.nationality.orEmpty())
        appTeamPlayers.add(appPlayer)
    }

    return AppTeamDetails(
        id, name, shortName, logo, founded.orEmpty(), venue.orEmpty(), appCoach, appTeamPlayers
    )
}