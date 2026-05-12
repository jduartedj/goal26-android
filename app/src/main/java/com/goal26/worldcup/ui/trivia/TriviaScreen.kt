package com.goal26.worldcup.ui.trivia

import android.app.Activity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goal26.worldcup.ui.components.AdBannerView
import com.goal26.worldcup.ui.components.RewardedAdHelper
import com.goal26.worldcup.ui.theme.Green
import com.goal26.worldcup.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaScreen(viewModel: TriviaViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("🧠 World Cup Trivia", fontWeight = FontWeight.Bold) }
        )

        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.isFinished -> {
                FinishedScreen(
                    score = uiState.score,
                    total = uiState.questions.size,
                    onRestart = { viewModel.restart() },
                    onBonusRound = {
                        val activity = context as? Activity
                        if (activity != null && RewardedAdHelper.isLoaded()) {
                            RewardedAdHelper.show(
                                activity,
                                onRewarded = { viewModel.startBonusRound() }
                            )
                        } else {
                            viewModel.startBonusRound()
                        }
                    }
                )
            }
            uiState.questions.isNotEmpty() -> {
                val question = uiState.questions[uiState.currentIndex]

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Ad at top of trivia
                    AdBannerView()
                    // Progress
                    LinearProgressIndicator(
                        progress = { (uiState.currentIndex + 1).toFloat() / uiState.questions.size },
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Question ${uiState.currentIndex + 1}/${uiState.questions.size}",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Row {
                            if (uiState.isBonusRound) {
                                Surface(
                                    shape = MaterialTheme.shapes.small,
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        "⭐ BONUS",
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(Modifier.width(8.dp))
                            }
                            Text(
                                "Score: ${uiState.score}",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    // Category chip
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            "${question.category} • ${question.difficulty}",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        question.question,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 28.sp
                    )

                    Spacer(Modifier.height(24.dp))

                    // Options
                    question.options.forEachIndexed { index, option ->
                        val selected = uiState.selectedAnswer == index
                        val isCorrect = index == question.correctIndex
                        val answered = uiState.selectedAnswer != null

                        val bgColor by animateColorAsState(
                            targetValue = when {
                                !answered -> MaterialTheme.colorScheme.surfaceVariant
                                isCorrect -> Green.copy(alpha = 0.2f)
                                selected -> Red.copy(alpha = 0.2f)
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            },
                            label = "optionColor"
                        )

                        val borderColor = when {
                            !answered -> MaterialTheme.colorScheme.outline
                            isCorrect -> Green
                            selected -> Red
                            else -> MaterialTheme.colorScheme.outline
                        }

                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = { viewModel.selectAnswer(index) },
                            colors = CardDefaults.outlinedCardColors(containerColor = bgColor),
                            border = CardDefaults.outlinedCardBorder().copy(
                                brush = androidx.compose.ui.graphics.SolidColor(borderColor)
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "${('A' + index)}.",
                                    fontWeight = FontWeight.Bold,
                                    color = borderColor
                                )
                                Spacer(Modifier.width(12.dp))
                                Text(option, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }

                    if (uiState.selectedAnswer != null) {
                        Spacer(Modifier.height(16.dp))
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                            )
                        ) {
                            Text(
                                "💡 ${question.explanation}",
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.nextQuestion() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                if (uiState.currentIndex < uiState.questions.size - 1)
                                    "Next Question →" else "See Results"
                            )
                        }
                    }

                    Spacer(Modifier.weight(1f))
                    AdBannerView()
                }
            }
        }
    }
}

@Composable
fun FinishedScreen(
    score: Int,
    total: Int,
    onRestart: () -> Unit,
    onBonusRound: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val emoji = when {
            score == total -> "🏆"
            score >= total * 0.8 -> "🥇"
            score >= total * 0.6 -> "🥈"
            score >= total * 0.4 -> "🥉"
            else -> "⚽"
        }

        Text(emoji, fontSize = 72.sp)
        Spacer(Modifier.height(16.dp))
        Text("Quiz Complete!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(
            "$score / $total correct",
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(8.dp))

        val message = when {
            score == total -> "Perfect score! You're a World Cup genius!"
            score >= total * 0.8 -> "Amazing! You really know your football!"
            score >= total * 0.6 -> "Great job! You're well on your way!"
            score >= total * 0.4 -> "Not bad! Keep learning!"
            else -> "Time to brush up on your World Cup knowledge!"
        }
        Text(message, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)

        Spacer(Modifier.height(32.dp))

        Button(onClick = onRestart, modifier = Modifier.fillMaxWidth()) {
            Text("🔄 Play Again (10 Questions)")
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(onClick = onBonusRound, modifier = Modifier.fillMaxWidth()) {
            Text("🎬 Bonus Round (5 Hard Questions)")
        }

        Spacer(Modifier.height(16.dp))
        AdBannerView()
    }
}
