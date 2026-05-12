package com.goal26.worldcup.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goal26.worldcup.domain.model.Match
import com.goal26.worldcup.domain.usecase.CountdownData
import com.goal26.worldcup.domain.usecase.DateTimeUtils
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(
    targetUtc: String,
    modifier: Modifier = Modifier
) {
    var countdown by remember { mutableStateOf(DateTimeUtils.getCountdown(targetUtc)) }

    LaunchedEffect(targetUtc) {
        while (!countdown.isExpired) {
            delay(1000)
            countdown = DateTimeUtils.getCountdown(targetUtc)
        }
    }

    if (!countdown.isExpired) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CountdownUnit(countdown.days, "DAYS")
            CountdownSeparator()
            CountdownUnit(countdown.hours, "HRS")
            CountdownSeparator()
            CountdownUnit(countdown.minutes, "MIN")
            CountdownSeparator()
            CountdownUnit(countdown.seconds, "SEC")
        }
    }
}

@Composable
fun CountdownUnit(value: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Text(
                "%02d".format(value),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun CountdownSeparator() {
    Text(":", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
}

@Composable
fun NextMatchCard(
    match: Match?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    match?.let { m ->
        Card(
            modifier = modifier.fillMaxWidth(),
            onClick = onClick,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "⏱️ NEXT MATCH",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(m.homeTeamCode, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(m.homeTeam, style = MaterialTheme.typography.bodySmall)
                    }
                    Text("vs", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(m.awayTeamCode, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(m.awayTeam, style = MaterialTheme.typography.bodySmall)
                    }
                }

                Spacer(Modifier.height(12.dp))

                CountdownTimer(m.kickoffUtc)

                Spacer(Modifier.height(8.dp))

                Text(
                    DateTimeUtils.formatFull(m.kickoffUtc),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    m.stadium,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
