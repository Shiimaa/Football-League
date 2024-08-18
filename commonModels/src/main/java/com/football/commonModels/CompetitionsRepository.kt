package com.football.commonModels

import com.football.commonModels.model.AppCompetition

interface CompetitionsRepository {
    suspend fun getCompetitions(): List<AppCompetition>

    suspend fun saveCompetitions(competitions: List<AppCompetition>)
}