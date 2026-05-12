package com.goal26.worldcup.ui.matches

import android.content.Intent
import android.provider.CalendarContract
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goal26.worldcup.domain.model.Match
import com.goal26.worldcup.domain.usecase.DateTimeUtils
import com.goal26.worldcup.ui.components.AdBannerView
import com.goal26.worldcup.ui.components.CountdownTimer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(
    matchId: Int,
    onBack: () -> Unit,
    viewModel: MatchesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val match = uiState.matches.find { it.id == matchId }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(match?.let { "${it.homeTeamCode} vs ${it.awayTeamCode}" } ?: "Match") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    match?.let { m ->
                        if (m.status.lowercase() == "scheduled") {
                            IconButton(onClick = { addToCalendar(context, m) }) {
                                Icon(Icons.Default.CalendarMonth, "Add to Calendar")
                            }
                        }
                        IconButton(onClick = { shareMatch(context, m) }) {
                            Icon(Icons.Default.Share, "Share")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            match?.let { m ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        m.groupName?.let {
                            Text(
                                "Group $it • Match ${m.matchNumber}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(Modifier.height(16.dp))
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(m.homeTeamCode, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                                Text(m.homeTeam, style = MaterialTheme.typography.bodyMedium)
                            }
                            if (m.homeScore != null && m.awayScore != null) {
                                Text(
                                    "${m.homeScore} - ${m.awayScore}",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 36.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            } else {
                                Text("vs", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(m.awayTeamCode, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                                Text(m.awayTeam, style = MaterialTheme.typography.bodyMedium)
                            }
                        }

                        // Countdown if scheduled
                        if (m.status.lowercase() == "scheduled") {
                            Spacer(Modifier.height(20.dp))
                            CountdownTimer(m.kickoffUtc)
                        }

                        Spacer(Modifier.height(24.dp))
                        HorizontalDivider()
                        Spacer(Modifier.height(16.dp))

                        InfoRow("🏟️ Stadium", m.stadium)
                        InfoRow("📍 City", m.city)
                        InfoRow("🕐 Kickoff", DateTimeUtils.formatFull(m.kickoffUtc))
                        InfoRow("📊 Status", m.status.replaceFirstChar { it.uppercase() })
                        InfoRow("🔢 Round", m.round.replaceFirstChar { it.uppercase() })
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Add to Calendar button
                if (m.status.lowercase() == "scheduled") {
                    OutlinedButton(
                        onClick = { addToCalendar(context, m) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.CalendarMonth, "Add to calendar", modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Add to Calendar")
                    }
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    onClick = { shareMatch(context, m) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Share, "Share match", modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Share Match")
                }

                Spacer(Modifier.height(16.dp))
                AdBannerView()
            } ?: run {
                Text("Match not found", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

private fun addToCalendar(context: android.content.Context, match: Match) {
    val instant = DateTimeUtils.parseUtc(match.kickoffUtc)
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, "⚽ ${match.homeTeam} vs ${match.awayTeam}")
        putExtra(CalendarContract.Events.EVENT_LOCATION, "${match.stadium}, ${match.city}")
        putExtra(CalendarContract.Events.DESCRIPTION, "FIFA World Cup 2026 - ${match.groupName?.let { "Group $it" } ?: match.round}\n\nPowered by Goal26")
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, instant.toEpochMilli())
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, instant.toEpochMilli() + 7200_000)
    }
    context.startActivity(intent)
}

private fun shareMatch(context: android.content.Context, match: Match) {
    val scoreText = if (match.homeScore != null && match.awayScore != null) {
        "${match.homeTeam} ${match.homeScore} - ${match.awayScore} ${match.awayTeam}"
    } else {
        "${match.homeTeam} vs ${match.awayTeam}"
    }
    val text = "⚽ FIFA World Cup 2026\n$scoreText\n🏟️ ${match.stadium}, ${match.city}\n🕐 ${DateTimeUtils.formatFull(match.kickoffUtc)}\n\nTrack with Goal26! 🏆"
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Share Match"))
}
