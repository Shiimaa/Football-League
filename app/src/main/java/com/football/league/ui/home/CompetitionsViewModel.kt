package com.football.league.ui.home

import com.football.core.Result
import com.football.core.repository.CompetitionsRepository
import com.football.commonModels.model.AppCompetition
import com.football.league.ui.BaseViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompetitionsViewModel @Inject constructor(private val competitionsRepository: CompetitionsRepository) :
    BaseViewHolder() {
    private val _uiState =
        MutableStateFlow<CompetitionsUiState<List<AppCompetition>>>(CompetitionsUiState.IsLoading)

    val uiState: StateFlow<CompetitionsUiState<List<AppCompetition>>> = _uiState

    fun getCompetitionsList() {
        scope.launch {
            val result = withContext(Dispatchers.IO) { competitionsRepository.getCompetitions() }
            when (result) {
                is Result.Success -> {
                    if (result.data.isNotEmpty())
                        _uiState.value = CompetitionsUiState.Success(result.data)
                    else
                        _uiState.value = CompetitionsUiState.Empty
                }

                is Result.Error -> {
                    _uiState.value = CompetitionsUiState.Error(result.throwable.message)
                }
            }
        }
    }


    sealed class CompetitionsUiState<out T> {
        data object IsLoading : CompetitionsUiState<Nothing>()
        data class Success<out T>(val data: T) : CompetitionsUiState<T>()
        data class Error(val t: String?) : CompetitionsUiState<Nothing>()
        data object Empty : CompetitionsUiState<Nothing>()
    }
}