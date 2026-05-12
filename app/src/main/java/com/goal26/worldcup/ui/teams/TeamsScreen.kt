package com.goal26.worldcup.ui.teams

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goal26.worldcup.domain.model.Team
import com.goal26.worldcup.ui.components.AdBannerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsScreen(
    viewModel: TeamsViewModel = hiltViewModel(),
    onTeamClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("🏆 Teams", fontWeight = FontWeight.Bold) }
        )

        // Search bar
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = { viewModel.search(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text("Search teams...") },
            leadingIcon = { Icon(Icons.Default.Search, "Search") },
            singleLine = true,
            shape = MaterialTheme.shapes.large
        )

        Spacer(Modifier.height(8.dp))

        // Group filter
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = uiState.selectedGroup == null,
                    onClick = { viewModel.filterByGroup(null) },
                    label = { Text("All (${uiState.teams.size})") }
                )
            }
            items(('A'..'L').toList()) { group ->
                FilterChip(
                    selected = uiState.selectedGroup == group.toString(),
                    onClick = { viewModel.filterByGroup(group.toString()) },
                    label = { Text("$group") }
                )
            }
        }

        when {
            uiState.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            else -> {
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.refresh() },
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item { AdBannerView() }
                        items(uiState.filteredTeams) { team ->
                            TeamCard(team = team, onClick = { onTeamClick(team.id) })
                        }
                        item { AdBannerView() }
                    }
                }
            }
        }
    }
}

@Composable
fun TeamCard(team: Team, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(team.flagEmoji, fontSize = 32.sp)
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(team.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    "${team.nickname} • Group ${team.group}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("#${team.fifaRanking}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Text(team.confederation, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
