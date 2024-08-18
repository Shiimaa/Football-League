package com.football.league.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.football.league.ui.competitionDetails.CompetitionDetailsViewModel
import com.football.league.ui.home.CompetitionsViewModel
import com.football.league.ui.teamDetails.TeamDetailsViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    competitionsViewModel: Provider<CompetitionsViewModel>,
    competitionDetailsViewModel: Provider<CompetitionDetailsViewModel>,
    teamDetailsViewModel: Provider<TeamDetailsViewModel>,
) : ViewModelProvider.Factory {
    private val providers = hashMapOf<Class<*>, Provider<out ViewModel>>(
        CompetitionsViewModel::class.java to competitionsViewModel,
        CompetitionDetailsViewModel::class.java to competitionDetailsViewModel,
        TeamDetailsViewModel::class.java to teamDetailsViewModel,

        )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }

}
