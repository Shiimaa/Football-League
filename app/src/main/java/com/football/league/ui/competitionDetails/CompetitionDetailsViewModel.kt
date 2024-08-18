package com.football.league.ui.competitionDetails

import com.football.core.Result
import com.football.commonModels.model.AppCompetitionDetails
import com.football.commonModels.model.AppCompetitionTeam
import com.football.core.repository.CompetitionDetailsRepository
import com.football.core.repository.TeamDetailsRepository
import com.football.league.ui.BaseViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompetitionDetailsViewModel @Inject constructor(
    private val competitionDetailsRepository: CompetitionDetailsRepository,
    private val teamDetailsRepository: TeamDetailsRepository
) : BaseViewHolder() {
    private val _uiState =
        MutableStateFlow<CompetitionDetailsUiState<AppCompetitionDetails>>(CompetitionDetailsUiState.IsLoading)

    val uiState: StateFlow<CompetitionDetailsUiState<AppCompetitionDetails>> = _uiState

    private val _uiTeamState =
        MutableStateFlow<CompetitionDetailsUiState<List<AppCompetitionTeam>>>(
            CompetitionDetailsUiState.IsLoading
        )

    val uiTeamState: StateFlow<CompetitionDetailsUiState<List<AppCompetitionTeam>>> = _uiTeamState

    fun getCompetitionsList(id: Long) {
        scope.launch {
            val result =
                withContext(Dispatchers.IO) { competitionDetailsRepository.getCompetitionDetails(id) }
            when (result) {
                is Result.Success -> {
                    _uiState.value = CompetitionDetailsUiState.Success(result.data)
                    getTeamDetails(id)
                }

                is Result.Error -> {
                    _uiState.value = CompetitionDetailsUiState.Error(result.throwable.message)
                }
            }
        }
    }

    private fun getTeamDetails(id: Long) {
        scope.launch {
            val result =
                withContext(Dispatchers.IO) { teamDetailsRepository.getCompetitionTeams(id) }
            when (result) {
                is Result.Success -> {
                    _uiTeamState.value = CompetitionDetailsUiState.Success(result.data)
                }

                is Result.Error -> {
                    _uiTeamState.value = CompetitionDetailsUiState.Error(result.throwable.message)
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