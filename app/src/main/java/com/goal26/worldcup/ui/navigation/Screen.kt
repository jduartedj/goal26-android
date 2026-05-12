package com.goal26.worldcup.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Matches : Screen("matches", "Matches", Icons.Filled.SportsSoccer, Icons.Outlined.SportsSoccer)
    data object Standings : Screen("standings", "Standings", Icons.Filled.Leaderboard, Icons.Outlined.Leaderboard)
    data object Teams : Screen("teams", "Teams", Icons.Filled.Groups, Icons.Outlined.Groups)
    data object Trivia : Screen("trivia", "Trivia", Icons.Filled.Quiz, Icons.Outlined.Quiz)
    data object Venues : Screen("venues", "Venues", Icons.Filled.Stadium, Icons.Outlined.Stadium)
    data object TeamDetail : Screen("team/{teamId}", "Team", Icons.Filled.Info, Icons.Outlined.Info)
    data object MatchDetail : Screen("match/{matchId}", "Match", Icons.Filled.Info, Icons.Outlined.Info)
}

val bottomNavItems = listOf(Screen.Matches, Screen.Standings, Screen.Teams, Screen.Trivia, Screen.Venues)
