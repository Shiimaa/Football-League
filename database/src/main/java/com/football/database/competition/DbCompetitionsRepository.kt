package com.football.database.competition

import android.content.Context
import com.football.commonModels.CompetitionsRepository
import com.football.commonModels.model.AppCompetition
import com.football.database.FootballDatabase
import com.football.database.utils.toAppCompetitions
import com.football.database.utils.toDbCompetitions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbCompetitionsRepository @Inject constructor(private val context: Context) :
    CompetitionsRepository {
    override suspend fun getCompetitions(): List<AppCompetition> {
        val competitions = FootballDatabase.getDatabase(context).competitionDao().getCompetitions()

        val appCompetitions = arrayListOf<AppCompetition>()
        competitions.forEach { competition ->
            appCompetitions.add(competition.toAppCompetitions())
        }

        return appCompetitions
    }

    override suspend fun saveCompetitions(competitions: List<AppCompetition>) {
        val dbCompetitions = arrayListOf<Competition>()
        competitions.forEach { competition ->
            dbCompetitions.add(competition.toDbCompetitions())
        }

        FootballDatabase.getDatabase(context).competitionDao().insertCompetitions(dbCompetitions)
    }

}
