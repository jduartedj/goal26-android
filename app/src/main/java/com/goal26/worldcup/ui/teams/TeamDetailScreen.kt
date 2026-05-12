package com.goal26.worldcup.ui.teams

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goal26.worldcup.data.repository.SquadData
import com.goal26.worldcup.data.repository.StaticData
import com.goal26.worldcup.domain.model.Match
import com.goal26.worldcup.domain.model.Player
import com.goal26.worldcup.domain.usecase.DateTimeUtils
import com.goal26.worldcup.ui.components.AdBannerView
import com.goal26.worldcup.ui.matches.MatchesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(
    teamId: String,
    onBack: () -> Unit,
    onMatchClick: (Int) -> Unit = {},
    viewModel: TeamsViewModel = hiltViewModel(),
    matchesViewModel: MatchesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val matchesState by matchesViewModel.uiState.collectAsState()
    val team = uiState.teams.find { it.id == teamId }
    val squad = SquadData.getSquad(teamId)
    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(false) }

    // Get team name for matching
    val teamName = team?.name ?: StaticData.teamCodes.entries.find { it.value == teamId }?.key ?: ""

    // Filter matches for this team
    val teamMatches = matchesState.matches.filter {
        it.homeTeamCode == teamId || it.awayTeamCode == teamId ||
        it.homeTeam == teamName || it.awayTeam == teamName
    }

    LaunchedEffect(teamId) {
        val prefs = context.getSharedPreferences("goal26_prefs", android.content.Context.MODE_PRIVATE)
        isFavorite = prefs.getStringSet("fav_teams", emptySet())?.contains(teamId) == true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(team?.name ?: "Team") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    team?.let { t ->
                        IconButton(onClick = {
                            val prefs = context.getSharedPreferences("goal26_prefs", android.content.Context.MODE_PRIVATE)
                            val favs = prefs.getStringSet("fav_teams", emptySet())?.toMutableSet() ?: mutableSetOf()
                            if (isFavorite) favs.remove(teamId) else favs.add(teamId)
                            prefs.edit().putStringSet("fav_teams", favs).apply()
                            isFavorite = !isFavorite
                        }) {
                            Icon(
                                if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                "Favorite",
                                tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                            )
                        }
                        IconButton(onClick = { shareTeam(context, t.name, t.group, t.fifaRanking) }) {
                            Icon(Icons.Default.Share, "Share")
                        }
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ad at top
            item { AdBannerView() }

            team?.let { t ->
                // Hero
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        Text(t.flagEmoji, fontSize = 72.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(t.name, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                        Text(
                            "\"${t.nickname}\"",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Info card
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            InfoRow("FIFA Code", t.code)
                            InfoRow("FIFA Ranking", "#${t.fifaRanking}")
                            InfoRow("Confederation", t.confederation)
                            InfoRow("Group", "Group ${t.group}")
                            InfoRow("Coach", t.coach)
                            InfoRow("Best WC Finish", t.bestFinish)
                            InfoRow("WC Appearances", "${t.worldCupAppearances}")
                        }
                    }
                }

                // === TEAM MATCHES SECTION ===
                if (teamMatches.isNotEmpty()) {
                    item {
                        Text(
                            "Scheduled Matches",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    items(teamMatches) { match ->
                        TeamMatchCard(match = match, teamCode = teamId, onClick = { onMatchClick(match.id) })
                    }

                    // Ad between matches and squad
                    item { AdBannerView() }
                }

                // === SQUAD SECTION ===
                squad?.let { s ->
                    item {
                        Text(
                            "Expected Squad",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    val positions = listOf("GK" to "Goalkeepers", "DF" to "Defenders", "MF" to "Midfielders", "FW" to "Forwards")
                    positions.forEach { (posCode, posLabel) ->
                        val playersInPos = s.players.filter { it.position == posCode }
                        if (playersInPos.isNotEmpty()) {
                            item {
                                Text(
                                    posLabel,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                            items(playersInPos) { player ->
                                PlayerCard(player)
                            }
                        }
                    }
                } ?: item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Text(
                            "Squad information will be available closer to the tournament.\nFinal squads announced late May 2026.",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Bottom ad
                item { AdBannerView() }
            } ?: item {
                Text("Team not found")
            }
        }
    }
}

@Composable
fun TeamMatchCard(match: Match, teamCode: String, onClick: () -> Unit) {
    val isHome = match.homeTeamCode == teamCode
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Group label
            match.groupName?.let {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                ) {
                    Text(
                        "Grp $it",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.width(8.dp))
            }

            // Opponent
            val opponent = if (isHome) match.awayTeam else match.homeTeam
            val opponentCode = if (isHome) match.awayTeamCode else match.homeTeamCode
            val prefix = if (isHome) "vs" else "@"

            Column(modifier = Modifier.weight(1f)) {
                Text("$prefix $opponent ($opponentCode)", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                Text(
                    "${DateTimeUtils.formatLocalDate(match.kickoffUtc)} • ${DateTimeUtils.formatLocalTime(match.kickoffUtc)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    match.stadium,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Score or status
            if (match.homeScore != null && match.awayScore != null) {
                Text(
                    "${match.homeScore}-${match.awayScore}",
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text(
                    DateTimeUtils.getRelativeTime(match.kickoffUtc),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Composable
fun PlayerCard(player: Player) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (player.isCaptain)
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        "${player.number}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(player.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                    if (player.isCaptain) {
                        Spacer(Modifier.width(4.dp))
                        Icon(Icons.Default.Star, "Captain", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(14.dp))
                        Text(" (C)", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelSmall)
                    }
                }
                Text(
                    player.club,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text("${player.caps} caps", style = MaterialTheme.typography.labelSmall)
                if (player.goals > 0) {
                    Text("${player.goals} goals", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

private fun shareTeam(context: android.content.Context, name: String, group: String, ranking: Int) {
    val text = "$name — FIFA World Cup 2026\nFIFA Ranking: #$ranking\nGroup $group\n\nTrack with Goal26!"
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Share Team"))
}
