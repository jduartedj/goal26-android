package com.goal26.worldcup.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey val code: String,
    val name: String,
    val group: String,
    val confederation: String,
    val flagEmoji: String,
    val flagUrl: String,
    val fifaRanking: Int,
    val coach: String,
    val nickname: String,
    val worldCupAppearances: Int,
    val bestFinish: String
)

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey val id: Int,
    val matchNumber: Int,
    val round: String,
    val groupName: String?,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamCode: String,
    val awayTeamCode: String,
    val stadium: String,
    val city: String,
    val kickoffUtc: String,
    val status: String,
    val homeScore: Int?,
    val awayScore: Int?
)

@Entity(tableName = "standings")
data class StandingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val group: String,
    val teamName: String,
    val teamCode: String,
    val played: Int,
    val won: Int,
    val drawn: Int,
    val lost: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int,
    val points: Int,
    val position: Int
)

@Entity(tableName = "venues")
data class VenueEntity(
    @PrimaryKey val id: String,
    val name: String,
    val city: String,
    val country: String,
    val capacity: Int,
    val imageUrl: String?,
    val latitude: Double,
    val longitude: Double
)

@Entity(tableName = "cache_meta")
data class CacheMetaEntity(
    @PrimaryKey val dataKey: String,
    val lastUpdated: Long,
    val ttlMs: Long = 300_000L // 5 min default
) {
    fun isExpired(): Boolean = System.currentTimeMillis() - lastUpdated > ttlMs
}
