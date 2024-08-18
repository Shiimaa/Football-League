package com.football.database.team

import android.content.Context
import com.football.commonModels.TeamDetailsRepository
import com.football.commonModels.model.AppCompetitionTeam
import com.football.commonModels.model.AppPlayer
import com.football.commonModels.model.AppTeamDetails
import com.football.database.FootballDatabase
import com.football.database.utils.toAppCoach
import com.football.database.utils.toAppCompetitionTeams
import com.football.database.utils.toAppPlayer
import com.football.database.utils.toAppTeamDetails
import com.football.database.utils.toDbCoach
import com.football.database.utils.toDbPlayer
import com.football.database.utils.toDbTeamDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbTeamsRepository @Inject constructor(private val context: Context) :
    TeamDetailsRepository {

    override suspend fun getCompetitionTeams(id: Long): List<AppCompetitionTeam> {

        val teams = FootballDatabase.getDatabase(context).teamDetailsDao().getCompetitionTeams(id)

        val appTeams = arrayListOf<AppCompetitionTeam>()
        teams.forEach { team ->
            appTeams.add(team.toAppCompetitionTeams())
        }

        return appTeams
    }

    override suspend fun getTeamDetails(competitionId: Long, teamId: Long): AppTeamDetails {
        val teamDetails =
            FootballDatabase.getDatabase(context).teamDetailsDao()
                .getTeamDetails(teamId, competitionId)

        val nonNullTeamDetails = teamDetails ?: throw Exception("team $teamId doesn't exists")

        val coach = FootballDatabase.getDatabase(context).coachDao().getCoach(teamId)

        val players =
            FootballDatabase.getDatabase(context).playerDao().getPlayers(teamId, competitionId)

        val appPlayers = arrayListOf<AppPlayer>()
        players.forEach { player ->
            appPlayers.add(player.toAppPlayer())
        }

        return nonNullTeamDetails.toAppTeamDetails(coach.toAppCoach(), appPlayers)
    }

    override suspend fun saveTeams(competitionDetails: List<AppTeamDetails>, competitionId: Long) {
        competitionDetails.forEach { team ->
            val players = team.players
            val dbPlayers = arrayListOf<Player>()
            players.forEach { player ->
                dbPlayers.add(player.toDbPlayer(team.id, competitionId))
            }

            val coach = team.coach.toDbCoach(team.id)

            val dbCompetitionTeam = team.toDbTeamDetails(competitionId)

            FootballDatabase.getDatabase(context).playerDao().insertPlayers(dbPlayers)
            FootballDatabase.getDatabase(context).coachDao().insertCoach(coach)
            FootballDatabase.getDatabase(context).teamDetailsDao().insertTeam(dbCompetitionTeam)
        }
    }

}
