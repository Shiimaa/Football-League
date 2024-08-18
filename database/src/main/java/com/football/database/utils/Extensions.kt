package com.football.database.utils

import com.football.commonModels.model.AppCoach
import com.football.commonModels.model.AppCompetition
import com.football.commonModels.model.AppCompetitionDetails
import com.football.commonModels.model.AppCompetitionTeam
import com.football.commonModels.model.AppPlayer
import com.football.commonModels.model.AppSeasonDetails
import com.football.commonModels.model.AppTeamDetails
import com.football.commonModels.model.AppWinnerTeam
import com.football.database.competition.Competition
import com.football.database.season.Season
import com.football.database.season.WinnerTeam
import com.football.database.team.Coach
import com.football.database.team.Player
import com.football.database.team.TeamDetails

fun Competition.toAppCompetitions(): AppCompetition {
    return AppCompetition(id, name, logo)
}

fun AppCompetition.toDbCompetitions(): Competition {
    return Competition(id = id, name = name, logo = logo)
}

fun TeamDetails.toAppTeamDetails(appCoach: AppCoach, appPlayers: List<AppPlayer>): AppTeamDetails {
    return AppTeamDetails(
        id, name, shortName, logo, founded, venue, appCoach, appPlayers
    )
}

fun TeamDetails.toAppCompetitionTeams(): AppCompetitionTeam {
    return AppCompetitionTeam(
        id, name, shortName, logo
    )
}

fun Coach.toAppCoach(): AppCoach {
    return AppCoach(id, name, nationality)
}

fun Player.toAppPlayer(): AppPlayer {
    return AppPlayer(id, name, position, nationality)
}

fun AppTeamDetails.toDbTeamDetails(competitionId: Long): TeamDetails {
    return TeamDetails(
        id, name, shortName, logo, founded, venue, competitionId
    )
}

fun AppCoach.toDbCoach(teamId: Long): Coach {
    return Coach(id, name, nationality, teamId)
}

fun AppPlayer.toDbPlayer(teamId: Long, competitionId: Long): Player {
    return Player(id, name, position, nationality, teamId, competitionId)
}

fun WinnerTeam.toAppWinnerTeam(): AppWinnerTeam {
    return AppWinnerTeam(id, name, logo)
}

fun Season.toAppSeason(appWinnerTeam: AppWinnerTeam?): AppSeasonDetails {
    return AppSeasonDetails(id, startDate, endDate, appWinnerTeam)
}

fun Competition.toAppCompetitionDetails(appSeasonDetails: AppSeasonDetails): AppCompetitionDetails {
    return AppCompetitionDetails(id, name, logo, appSeasonDetails)
}

fun AppWinnerTeam.toDbWinnerTeam(seasonId: Long, competitionId: Long): WinnerTeam {
    return WinnerTeam(id, name, logo, seasonId, competitionId)
}

fun AppSeasonDetails.toDbSeason(competitionId: Long): Season {
    return Season(id, startDate, endDate, competitionId)
}

fun AppCompetitionDetails.toDbCompetitionDetails(): Competition {
    return Competition(id, name, logo)
}



