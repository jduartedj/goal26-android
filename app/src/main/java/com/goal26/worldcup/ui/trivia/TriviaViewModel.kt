package com.goal26.worldcup.ui.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goal26.worldcup.data.repository.WorldCupRepository
import com.goal26.worldcup.domain.model.TriviaQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TriviaUiState(
    val questions: List<TriviaQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val score: Int = 0,
    val selectedAnswer: Int? = null,
    val isFinished: Boolean = false,
    val isBonusRound: Boolean = false,
    val isLoading: Boolean = true
)

@HiltViewModel
class TriviaViewModel @Inject constructor(
    private val repository: WorldCupRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TriviaUiState())
    val uiState: StateFlow<TriviaUiState> = _uiState.asStateFlow()
    private var allQuestions: List<TriviaQuestion> = emptyList()

    init { loadTrivia() }

    fun loadTrivia() {
        viewModelScope.launch {
            allQuestions = repository.getTriviaQuestions()
            val selected = allQuestions.shuffled().take(10)
            _uiState.value = TriviaUiState(questions = selected, isLoading = false)
        }
    }

    fun selectAnswer(index: Int) {
        val state = _uiState.value
        if (state.selectedAnswer != null) return
        val correct = state.questions[state.currentIndex].correctIndex == index
        val scoreBonus = if (state.isBonusRound) 2 else 1
        _uiState.value = state.copy(
            selectedAnswer = index,
            score = if (correct) state.score + scoreBonus else state.score
        )
    }

    fun nextQuestion() {
        val state = _uiState.value
        val nextIndex = state.currentIndex + 1
        if (nextIndex >= state.questions.size) {
            _uiState.value = state.copy(isFinished = true)
        } else {
            _uiState.value = state.copy(currentIndex = nextIndex, selectedAnswer = null)
        }
    }

    fun startBonusRound() {
        val hardQuestions = allQuestions
            .filter { it.difficulty == "Hard" }
            .shuffled()
            .take(5)
        _uiState.value = TriviaUiState(
            questions = hardQuestions,
            isBonusRound = true,
            isLoading = false,
            score = _uiState.value.score // carry over score
        )
    }

    fun restart() {
        loadTrivia()
    }
}
