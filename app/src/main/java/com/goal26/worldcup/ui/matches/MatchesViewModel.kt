package com.goal26.worldcup.ui.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goal26.worldcup.data.repository.WorldCupRepository
import com.goal26.worldcup.domain.model.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MatchesUiState(
    val matches: List<Match> = emptyList(),
    val filteredMatches: List<Match> = emptyList(),
    val nextMatch: Match? = null,
    val selectedGroup: String? = null,
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val repository: WorldCupRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MatchesUiState())
    val uiState: StateFlow<MatchesUiState> = _uiState.asStateFlow()

    init {
        loadMatches()
    }

    fun loadMatches() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val matches = repository.getMatches()
                val nextMatch = repository.getNextMatch() ?: matches.firstOrNull { it.status == "scheduled" }
                _uiState.value = _uiState.value.copy(
                    matches = matches,
                    filteredMatches = matches,
                    nextMatch = nextMatch,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)
            try {
                val matches = repository.getMatches(forceRefresh = true)
                val nextMatch = repository.getNextMatch() ?: matches.firstOrNull { it.status == "scheduled" }
                _uiState.value = _uiState.value.copy(
                    matches = matches,
                    filteredMatches = applyGroupFilter(matches, _uiState.value.selectedGroup),
                    nextMatch = nextMatch,
                    isRefreshing = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isRefreshing = false, error = e.message)
            }
        }
    }

    fun filterByGroup(group: String?) {
        val all = _uiState.value.matches
        _uiState.value = _uiState.value.copy(
            selectedGroup = group,
            filteredMatches = applyGroupFilter(all, group)
        )
    }

    private fun applyGroupFilter(matches: List<Match>, group: String?): List<Match> {
        return if (group == null) matches else matches.filter { it.groupName == group }
    }
}
