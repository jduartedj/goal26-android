package com.goal26.worldcup.ui.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goal26.worldcup.data.repository.WorldCupRepository
import com.goal26.worldcup.domain.model.GroupStanding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StandingsUiState(
    val standings: List<GroupStanding> = emptyList(),
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val repository: WorldCupRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(StandingsUiState())
    val uiState: StateFlow<StandingsUiState> = _uiState.asStateFlow()

    init { loadStandings() }

    fun loadStandings() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val standings = repository.getStandings()
                _uiState.value = StandingsUiState(standings = standings, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = StandingsUiState(isLoading = false, error = e.message)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)
            try {
                val standings = repository.getStandings(forceRefresh = true)
                _uiState.value = StandingsUiState(standings = standings, isRefreshing = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isRefreshing = false, error = e.message)
            }
        }
    }
}
