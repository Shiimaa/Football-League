package com.football.league.ui.teamDetails

import com.football.core.Result
import com.football.commonModels.model.AppTeamDetails
import com.football.core.repository.TeamDetailsRepository
import com.football.league.ui.BaseViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamDetailsViewModel @Inject constructor(
    private val teamDetailsRepository: TeamDetailsRepository
) : BaseViewHolder() {
    private val _uiState =
        MutableStateFlow<CompetitionDetailsUiState<AppTeamDetails>>(CompetitionDetailsUiState.IsLoading)

    val uiState: StateFlow<CompetitionDetailsUiState<AppTeamDetails>> = _uiState


    fun getTeamDetails(competitionId: Long, teamId: Long) {
        scope.launch {
            val result =
                withContext(Dispatchers.IO) {
                    teamDetailsRepository.getTeamDetails(
                        competitionId,
                        teamId
                    )
                }
            when (result) {
                is Result.Success -> {
                    _uiState.value = CompetitionDetailsUiState.Success(result.data)
                }

                is Result.Error -> {
                    _uiState.value = CompetitionDetailsUiState.Error(result.throwable.message)
                }
            }
        }
    }


    sealed class CompetitionDetailsUiState<out T> {
        data object IsLoading : CompetitionDetailsUiState<Nothing>()
        data class Success<out T>(val data: T) : CompetitionDetailsUiState<T>()
        data class Error(val t: String?) : CompetitionDetailsUiState<Nothing>()
    }
}