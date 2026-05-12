package com.goal26.worldcup.data.db

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages user preferences: favorite teams, theme, etc.
 */
@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("goal26_prefs", Context.MODE_PRIVATE)

    // Favorite teams
    fun getFavoriteTeams(): Set<String> = prefs.getStringSet("fav_teams", emptySet()) ?: emptySet()

    fun toggleFavorite(teamCode: String) {
        val current = getFavoriteTeams().toMutableSet()
        if (current.contains(teamCode)) current.remove(teamCode) else current.add(teamCode)
        prefs.edit().putStringSet("fav_teams", current).apply()
    }

    fun isFavorite(teamCode: String): Boolean = getFavoriteTeams().contains(teamCode)

    // Theme
    fun isDarkMode(): Boolean = prefs.getBoolean("dark_mode", true)
    fun setDarkMode(enabled: Boolean) = prefs.edit().putBoolean("dark_mode", enabled).apply()

    // Onboarding
    fun hasSeenOnboarding(): Boolean = prefs.getBoolean("seen_onboarding", false)
    fun setSeenOnboarding() = prefs.edit().putBoolean("seen_onboarding", true).apply()
}
