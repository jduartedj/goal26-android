package com.goal26.worldcup.ui.matches

import android.content.Intent
import android.provider.CalendarContract
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goal26.worldcup.domain.model.Match
import com.goal26.worldcup.domain.usecase.DateTimeUtils
import com.goal26.worldcup.ui.components.AdBannerView
import com.goal26.worldcup.ui.components.NextMatchCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = hiltViewModel(),
    onMatchClick: (Int) -> Unit,
    isDark: Boolean = true,
    onToggleTheme: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("⚽ Goal26", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            actions = {
                IconButton(onClick = onToggleTheme) {
                    Icon(
                        if (isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                        contentDescription = "Toggle theme"
                    )
                }
            }
        )

        // Group filter chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = uiState.selectedGroup == null,
                    onClick = { viewModel.filterByGroup(null) },
                    label = { Text("All") }
                )
            }
            items(('A'..'L').toList()) { group ->
                FilterChip(
                    selected = uiState.selectedGroup == group.toString(),
                    onClick = { viewModel.filterByGroup(group.toString()) },
                    label = { Text("Group $group") }
                )
            }
        }

        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
            uiState.error != null && uiState.matches.isEmpty() -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.refresh() }) { Text("Retry") }
                    }
                }
            }
            else -> {
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refresh() },
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Next match countdown (only on "All" filter)
                        if (uiState.selectedGroup == null && uiState.nextMatch != null) {
                            item {
                                NextMatchCard(
                                    match = uiState.nextMatch,
                                    onClick = { uiState.nextMatch?.let { onMatchClick(it.id) } }
                                )
                                Spacer(Modifier.height(8.dp))
                            }
                        }

                        items(uiState.filteredMatches) { match ->
                            MatchCard(match = match, onClick = { onMatchClick(match.id) })
                        }

                        item {
                            Spacer(Modifier.height(8.dp))
                            AdBannerView()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MatchCard(match: Match, onClick: () -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    match.groupName?.let {
                        Text(
                            "Group $it",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    StatusChip(match.status)
                    if (match.status.lowercase() == "scheduled") {
                        Spacer(Modifier.width(4.dp))
                        IconButton(
                            onClick = { addToCalendar(context, match) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                Icons.Default.CalendarMonth, "Add to Calendar",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(match.homeTeamCode, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(match.homeTeam, style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (match.homeScore != null && match.awayScore != null) {
                        Text(
                            "${match.homeScore} - ${match.awayScore}",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        Text(
                            DateTimeUtils.formatLocalTime(match.kickoffUtc),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(match.awayTeamCode, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(match.awayTeam, style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                DateTimeUtils.formatLocalDate(match.kickoffUtc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "${match.stadium} • ${match.city}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            if (match.status.lowercase() == "scheduled") {
                Spacer(Modifier.height(4.dp))
                Text(
                    DateTimeUtils.getRelativeTime(match.kickoffUtc),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun StatusChip(status: String) {
    val (color, text) = when (status.lowercase()) {
        "live", "in_play" -> MaterialTheme.colorScheme.error to "🔴 LIVE"
        "finished", "completed" -> MaterialTheme.colorScheme.tertiary to "FT"
        "halftime" -> MaterialTheme.colorScheme.secondary to "HT"
        else -> MaterialTheme.colorScheme.onSurfaceVariant to status.uppercase()
    }
    Surface(
        shape = MaterialTheme.shapes.small,
        color = color.copy(alpha = 0.15f)
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontWeight = FontWeight.Bold
        )
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
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, instant.toEpochMilli() + 7200_000) // 2 hours
    }
    context.startActivity(intent)
}
