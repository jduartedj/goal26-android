package com.goal26.worldcup.ui.venues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goal26.worldcup.data.repository.WorldCupRepository
import com.goal26.worldcup.domain.model.Venue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class VenuesUiState(
    val venues: List<Venue> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val repository: WorldCupRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(VenuesUiState())
    val uiState: StateFlow<VenuesUiState> = _uiState.asStateFlow()

    init { loadVenues() }

    fun loadVenues() {
        viewModelScope.launch {
            try {
                val venues = repository.getVenues()
                _uiState.value = VenuesUiState(venues = venues, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = VenuesUiState(isLoading = false, error = e.message)
            }
        }
    }
}
