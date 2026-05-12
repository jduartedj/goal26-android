package com.goal26.worldcup.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BracketMatch(
    val id: String,
    val team1: String,
    val team2: String,
    val score1: Int? = null,
    val score2: Int? = null,
    val round: String,
    val matchInfo: String = ""
)

@Composable
fun KnockoutBracket(
    modifier: Modifier = Modifier
) {
    val rounds = listOf(
        "Round of 32" to generateR32Matches(),
        "Round of 16" to List(8) { BracketMatch("r16_${it+1}", "Winner R32", "Winner R32", round = "R16") },
        "Quarter-finals" to List(4) { BracketMatch("qf_${it+1}", "Winner R16", "Winner R16", round = "QF") },
        "Semi-finals" to List(2) { BracketMatch("sf_${it+1}", "Winner QF", "Winner QF", round = "SF") },
        "Final" to listOf(BracketMatch("final", "Winner SF1", "Winner SF2", round = "Final", matchInfo = "MetLife Stadium, July 19"))
    )

    Column(modifier = modifier) {
        Text(
            "🏆 Knockout Bracket",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            "32 teams advance from group stage → Round of 32 → R16 → QF → SF → Final",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                rounds.forEach { (roundName, matches) ->
                    BracketRound(roundName = roundName, matches = matches)
                }
            }
        }
    }
}

@Composable
fun BracketRound(roundName: String, matches: List<BracketMatch>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(160.dp)
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Text(
                roundName,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(Modifier.height(8.dp))

        matches.forEach { match ->
            BracketMatchCard(match)
        }
    }
}

@Composable
fun BracketMatchCard(match: BracketMatch) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            BracketTeamRow(match.team1, match.score1)
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 2.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            )
            BracketTeamRow(match.team2, match.score2)
            if (match.matchInfo.isNotBlank()) {
                Text(
                    match.matchInfo,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun BracketTeamRow(team: String, score: Int?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            team,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (score != null) FontWeight.Medium else FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        score?.let {
            Text(
                it.toString(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private fun generateR32Matches(): List<BracketMatch> {
    val matchups = listOf(
        "1A vs 3C/D/E" to "R32-1", "2B vs 2F" to "R32-2",
        "1C vs 3A/B/F" to "R32-3", "2D vs 2E" to "R32-4",
        "1E vs 3B/G/H" to "R32-5", "2A vs 2C" to "R32-6",
        "1G vs 3D/E/F" to "R32-7", "2H vs 2I" to "R32-8",
        "1B vs 3A/G/H" to "R32-9", "2L vs 2K" to "R32-10",
        "1D vs 3I/J/K" to "R32-11", "2F vs 2G" to "R32-12",
        "1I vs 3J/K/L" to "R32-13", "2J vs 2L" to "R32-14",
        "1K vs 3F/I/L" to "R32-15", "1L vs 1J" to "R32-16"
    )
    return matchups.map { (matchup, id) ->
        val teams = matchup.split(" vs ")
        BracketMatch(id, teams[0], teams.getOrElse(1) { "TBD" }, round = "R32")
    }
}
