package com.goal26.worldcup.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.goal26.worldcup.data.db.UserPreferences
import com.goal26.worldcup.ui.matches.MatchesScreen
import com.goal26.worldcup.ui.matches.MatchDetailScreen
import com.goal26.worldcup.ui.navigation.Screen
import com.goal26.worldcup.ui.navigation.bottomNavItems
import com.goal26.worldcup.ui.standings.StandingsScreen
import com.goal26.worldcup.ui.teams.TeamsScreen
import com.goal26.worldcup.ui.teams.TeamDetailScreen
import com.goal26.worldcup.ui.trivia.TriviaScreen
import com.goal26.worldcup.ui.venues.VenuesScreen
import com.goal26.worldcup.ui.theme.Goal26Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDark by remember { mutableStateOf(userPreferences.isDarkMode()) }

            Goal26Theme(darkTheme = isDark) {
                Goal26App(
                    isDark = isDark,
                    onToggleTheme = {
                        isDark = !isDark
                        userPreferences.setDarkMode(isDark)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Goal26App(isDark: Boolean, onToggleTheme: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = bottomNavItems.any {
        currentDestination?.hierarchy?.any { dest -> dest.route == it.route } == true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    if (selected) screen.selectedIcon else screen.unselectedIcon,
                                    contentDescription = screen.title
                                )
                            },
                            label = { Text(screen.title) },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Matches.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally(initialOffsetX = { 100 }) },
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally(initialOffsetX = { -100 }) },
            popExitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally(targetOffsetX = { 100 }) }
        ) {
            composable(Screen.Matches.route) {
                MatchesScreen(
                    onMatchClick = { matchId -> navController.navigate("match/$matchId") },
                    isDark = isDark,
                    onToggleTheme = onToggleTheme
                )
            }
            composable(Screen.Standings.route) { StandingsScreen() }
            composable(Screen.Teams.route) {
                TeamsScreen(
                    onTeamClick = { teamId -> navController.navigate("team/$teamId") }
                )
            }
            composable(Screen.Trivia.route) { TriviaScreen() }
            composable(Screen.Venues.route) { VenuesScreen() }
            composable(
                Screen.TeamDetail.route,
                arguments = listOf(navArgument("teamId") { type = NavType.StringType })
            ) { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId") ?: ""
                TeamDetailScreen(
                    teamId = teamId,
                    onBack = { navController.popBackStack() },
                    onMatchClick = { matchId -> navController.navigate("match/$matchId") }
                )
            }
            composable(
                Screen.MatchDetail.route,
                arguments = listOf(navArgument("matchId") { type = NavType.IntType })
            ) { backStackEntry ->
                val matchId = backStackEntry.arguments?.getInt("matchId") ?: 0
                MatchDetailScreen(
                    matchId = matchId,
                    onBack = { navController.popBackStack() },
                    onTeamClick = { teamCode -> navController.navigate("team/$teamCode") }
                )
            }
        }
    }
}
