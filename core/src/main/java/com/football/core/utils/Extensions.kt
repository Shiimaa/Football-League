package com.football.core.utils

import com.football.commonModels.model.AppCompetitionTeam
import com.football.commonModels.model.AppTeamDetails

fun AppTeamDetails.toAppCompetitionTeam(): AppCompetitionTeam {
    return AppCompetitionTeam(id, name, shortName, logo)
}
