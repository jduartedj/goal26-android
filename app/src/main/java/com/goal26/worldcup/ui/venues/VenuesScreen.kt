package com.goal26.worldcup.ui.venues

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goal26.worldcup.domain.model.Venue
import com.goal26.worldcup.ui.components.AdBannerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenuesScreen(viewModel: VenuesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("🏟️ Venues", fontWeight = FontWeight.Bold) }
        )

        when {
            uiState.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { AdBannerView() }
                    items(uiState.venues.take(8)) { venue ->
                        VenueCard(venue)
                    }
                    item { AdBannerView() }
                    items(uiState.venues.drop(8)) { venue ->
                        VenueCard(venue)
                    }
                    item { AdBannerView() }
                }
            }
        }
    }
}

@Composable
fun VenueCard(venue: Venue) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(venue.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        "${venue.city}, ${venue.country}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                val countryFlag = when (venue.country) {
                    "Mexico" -> "🇲🇽"
                    "Canada" -> "🇨🇦"
                    else -> "🇺🇸"
                }
                Text(countryFlag, fontSize = 28.sp)
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Capacity", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        "%,d".format(venue.capacity),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Location", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        "%.2f°, %.2f°".format(venue.latitude, venue.longitude),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
