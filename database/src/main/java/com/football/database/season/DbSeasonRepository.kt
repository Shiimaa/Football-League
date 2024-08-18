package com.football.database.season

import android.content.Context
import com.football.commonModels.CompetitionDetailsRepository
import com.football.commonModels.model.AppCompetitionDetails
import com.football.database.FootballDatabase
import com.football.database.utils.toAppCompetitionDetails
import com.football.database.utils.toAppSeason
import com.football.database.utils.toAppWinnerTeam
import com.football.database.utils.toDbSeason
import com.football.database.utils.toDbWinnerTeam
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbSeasonRepository @Inject constructor(private val context: Context) :
    CompetitionDetailsRepository {
    override suspend fun getCompetitionDetails(id: Long): AppCompetitionDetails {

        val competition = FootballDatabase.getDatabase(context).competitionDao().getCompetition(id)

        val nonNullCompetition = competition ?: throw Exception("Competition $id doesn't exists")

        val currentSeason =
            FootballDatabase.getDatabase(context).seasonDao().getSeason(nonNullCompetition.id)

        val nonNullCurrentSeason =
            currentSeason ?: throw Exception("CurrentSeason for Competition  $id doesn't exists")

        val winnerTeam = FootballDatabase.getDatabase(context).winnerTeamDao()
            .getWinnerTeam(nonNullCurrentSeason.id, nonNullCompetition.id)

        val appWinnerTeam = winnerTeam?.toAppWinnerTeam()

        return competition.toAppCompetitionDetails(nonNullCurrentSeason.toAppSeason(appWinnerTeam))
    }

    override suspend fun saveCompetition(competitionDetails: AppCompetitionDetails) {
        val dbSeason = competitionDetails.currentSeason.toDbSeason(competitionDetails.id)
        val dbWinnerTeam = competitionDetails.currentSeason.winnerTeam?.toDbWinnerTeam(
            competitionDetails.currentSeason.id,
            competitionDetails.id
        )

        FootballDatabase.getDatabase(context).seasonDao().insertSeason(dbSeason)
        dbWinnerTeam?.let {
            FootballDatabase.getDatabase(context).winnerTeamDao().insertWinnerTeam(it)
        }

    }

}
