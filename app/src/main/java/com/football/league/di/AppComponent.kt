package com.football.league.di

import com.football.commonModels.di.AppModule
import com.football.core.di.BaseComponent
import com.football.core.repository.CompetitionsRepository
import com.football.core.repository.CompetitionDetailsRepository
import com.football.core.repository.TeamDetailsRepository
import com.football.league.App
import com.football.league.ui.competitionDetails.CompetitionDetailsViewModel
import com.football.league.ui.home.CompetitionsViewModel
import com.football.league.ui.teamDetails.TeamDetailsViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : BaseComponent {
    fun inject(app: App)

    fun getCompetitionsRepository(): CompetitionsRepository

    fun getCompetitionDetailsRepository(): CompetitionDetailsRepository

    fun getTeamDetailsRepository(): TeamDetailsRepository

    fun viewModelsFactory(): ViewModelFactory

    fun competitionsViewModel(): CompetitionsViewModel

    fun competitionDetailsViewModel(): CompetitionDetailsViewModel

    fun teamDetailsViewModel(): TeamDetailsViewModel


}