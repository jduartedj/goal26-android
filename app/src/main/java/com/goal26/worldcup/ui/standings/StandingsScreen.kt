package com.goal26.worldcup.ui.standings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goal26.worldcup.domain.model.GroupStanding
import com.goal26.worldcup.domain.model.TeamStanding
import com.goal26.worldcup.ui.components.AdBannerView
import com.goal26.worldcup.ui.components.KnockoutBracket
import com.goal26.worldcup.ui.theme.GroupColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandingsScreen(viewModel: StandingsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("📊 Standings", fontWeight = FontWeight.Bold) }
        )

        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
            uiState.error != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
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
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Ad at top
                    item { AdBannerView() }
                    items(uiState.standings.take(6)) { group ->
                        GroupTable(group)
                    }
                    // Mid-content ad between group 6 and 7
                    item { AdBannerView() }
                    items(uiState.standings.drop(6)) { group ->
                        GroupTable(group)
                    }
                    item {
                        KnockoutBracket(modifier = Modifier.padding(vertical = 8.dp))
                    }
                    item {
                        AdBannerView()
                    }
                }
                }
            }
        }
    }
}

@Composable
fun GroupTable(group: GroupStanding) {
    val groupIndex = group.group.first() - 'A'
    val accentColor = GroupColors.getOrElse(groupIndex) { MaterialTheme.colorScheme.primary }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "Group ${group.group}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = accentColor
            )
            Spacer(Modifier.height(8.dp))

            // Header
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("#", Modifier.width(24.dp), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
                Text("Team", Modifier.weight(1f), fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
                TableHeader("P")
                TableHeader("W")
                TableHeader("D")
                TableHeader("L")
                TableHeader("GD")
                TableHeader("Pts")
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            group.teams.forEachIndexed { index, team ->
                val qualified = index < 2
                val potential = index == 2
                StandingRow(team, qualified, potential)
                if (index < group.teams.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp), color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                }
            }
        }
    }
}

@Composable
fun RowScope.TableHeader(text: String) {
    Text(
        text,
        modifier = Modifier.width(32.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
fun StandingRow(team: TeamStanding, qualified: Boolean, potential: Boolean) {
    val bgColor = when {
        qualified -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        potential -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.05f)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    Surface(color = bgColor, shape = MaterialTheme.shapes.extraSmall) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${team.position}", Modifier.width(24.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Text(
                "${team.teamCode} ${team.teamName}",
                Modifier.weight(1f),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (qualified) FontWeight.Bold else FontWeight.Normal
            )
            StatCell(team.played.toString())
            StatCell(team.won.toString())
            StatCell(team.drawn.toString())
            StatCell(team.lost.toString())
            StatCell(if (team.goalDifference >= 0) "+${team.goalDifference}" else "${team.goalDifference}")
            Text(
                "${team.points}",
                modifier = Modifier.width(32.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun StatCell(value: String) {
    Text(
        value,
        modifier = Modifier.width(32.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
