package com.goal26.worldcup.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goal26.worldcup.data.repository.WorldCupRepository
import com.goal26.worldcup.domain.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TeamsUiState(
    val teams: List<Team> = emptyList(),
    val filteredTeams: List<Team> = emptyList(),
    val searchQuery: String = "",
    val selectedGroup: String? = null,
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val repository: WorldCupRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TeamsUiState())
    val uiState: StateFlow<TeamsUiState> = _uiState.asStateFlow()

    init { loadTeams() }

    fun loadTeams() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val teams = repository.getTeams()
                _uiState.value = TeamsUiState(teams = teams, filteredTeams = teams, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = TeamsUiState(isLoading = false, error = e.message)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)
            try {
                val teams = repository.getTeams(forceRefresh = true)
                val state = _uiState.value
                _uiState.value = state.copy(teams = teams, isRefreshing = false)
                applyFilters()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isRefreshing = false, error = e.message)
            }
        }
    }

    fun search(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        applyFilters()
    }

    fun filterByGroup(group: String?) {
        _uiState.value = _uiState.value.copy(selectedGroup = group)
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        var filtered = state.teams
        state.selectedGroup?.let { g -> filtered = filtered.filter { it.group == g } }
        if (state.searchQuery.isNotBlank()) {
            val q = state.searchQuery.lowercase()
            filtered = filtered.filter { it.name.lowercase().contains(q) || it.nickname.lowercase().contains(q) }
        }
        _uiState.value = state.copy(filteredTeams = filtered)
    }
}
