package com.goal26.worldcup.data.repository

import com.goal26.worldcup.BuildConfig
import com.goal26.worldcup.data.api.Wc2026ApiService
import com.goal26.worldcup.data.db.WorldCupDao
import com.goal26.worldcup.data.db.entity.*
import com.goal26.worldcup.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorldCupRepository @Inject constructor(
    private val apiService: Wc2026ApiService,
    private val dao: WorldCupDao
) {
    private val authHeader = "Bearer ${BuildConfig.WC2026_API_KEY}"

    // ---- TEAMS ----
    suspend fun getTeams(forceRefresh: Boolean = false): List<Team> = withContext(Dispatchers.IO) {
        val cache = dao.getCacheMeta("teams")
        if (!forceRefresh && cache != null && !cache.isExpired()) {
            return@withContext dao.getAllTeams().first().map { it.toDomain() }
        }
        try {
            val apiTeams = apiService.getTeams(authHeader)
            val entities = apiTeams.map { api ->
                val meta = StaticData.teamMeta[api.code]
                TeamEntity(
                    code = api.code, name = api.name, group = api.group,
                    confederation = meta?.confederation ?: "Unknown",
                    flagEmoji = meta?.flagEmoji ?: "🏳️",
                    flagUrl = "https://flagcdn.com/w160/${api.code.lowercase()}.png",
                    fifaRanking = meta?.ranking ?: 0, coach = meta?.coach ?: "TBD",
                    nickname = meta?.nickname ?: "", worldCupAppearances = meta?.wcAppearances ?: 0,
                    bestFinish = meta?.bestFinish ?: "N/A"
                )
            }
            dao.insertTeams(entities)
            dao.insertCacheMeta(CacheMetaEntity("teams", System.currentTimeMillis(), 3600_000L))
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            val cached = dao.getAllTeams().first()
            if (cached.isNotEmpty()) cached.map { it.toDomain() } else StaticData.allTeams
        }
    }

    // ---- MATCHES ----
    suspend fun getMatches(forceRefresh: Boolean = false): List<Match> = withContext(Dispatchers.IO) {
        val cache = dao.getCacheMeta("matches")
        if (!forceRefresh && cache != null && !cache.isExpired()) {
            return@withContext dao.getAllMatches().first().map { it.toDomain() }
        }
        try {
            val apiMatches = apiService.getMatches(authHeader)
            val entities = apiMatches.map { api ->
                val city = StaticData.stadiumCities[api.stadium] ?: ""
                val homeCode = StaticData.teamCodes[api.homeTeam] ?: ""
                val awayCode = StaticData.teamCodes[api.awayTeam] ?: ""
                MatchEntity(
                    id = api.id, matchNumber = api.matchNumber, round = api.round,
                    groupName = api.groupName, homeTeam = api.homeTeam, awayTeam = api.awayTeam,
                    homeTeamCode = homeCode, awayTeamCode = awayCode,
                    stadium = api.stadium, city = city,
                    kickoffUtc = api.kickoffUtc, status = api.status,
                    homeScore = api.homeScore, awayScore = api.awayScore
                )
            }
            dao.insertMatches(entities)
            dao.insertCacheMeta(CacheMetaEntity("matches", System.currentTimeMillis(), 300_000L))
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            val cached = dao.getAllMatches().first()
            if (cached.isNotEmpty()) cached.map { it.toDomain() } else StaticData.allMatches
        }
    }

    // ---- STANDINGS ----
    suspend fun getStandings(forceRefresh: Boolean = false): List<GroupStanding> = withContext(Dispatchers.IO) {
        val cache = dao.getCacheMeta("standings")
        if (!forceRefresh && cache != null && !cache.isExpired()) {
            val entities = dao.getAllStandings().first()
            return@withContext entitiesToGroupStandings(entities)
        }
        try {
            val apiStandings = apiService.getStandings(authHeader)
            val entities = apiStandings.flatMap { api ->
                api.teams.map { t ->
                    StandingEntity(
                        group = api.group, teamName = t.team,
                        teamCode = StaticData.teamCodes[t.team] ?: "",
                        played = t.played, won = t.won, drawn = t.drawn, lost = t.lost,
                        goalsFor = t.goalsFor, goalsAgainst = t.goalsAgainst,
                        goalDifference = t.goalDifference, points = t.points, position = t.position
                    )
                }
            }
            dao.insertStandings(entities)
            dao.insertCacheMeta(CacheMetaEntity("standings", System.currentTimeMillis(), 300_000L))
            entitiesToGroupStandings(entities)
        } catch (e: Exception) {
            val cached = dao.getAllStandings().first()
            if (cached.isNotEmpty()) entitiesToGroupStandings(cached) else StaticData.initialStandings
        }
    }

    // ---- VENUES ----
    suspend fun getVenues(): List<Venue> = withContext(Dispatchers.IO) {
        val cached = dao.getAllVenues().first()
        if (cached.isNotEmpty()) return@withContext cached.map { it.toDomain() }
        val entities = StaticData.venues.map { v ->
            VenueEntity(v.id, v.name, v.city, v.country, v.capacity, v.imageUrl, v.latitude, v.longitude)
        }
        dao.insertVenues(entities)
        StaticData.venues
    }

    suspend fun getNextMatch(): Match? = withContext(Dispatchers.IO) {
        dao.getNextMatch()?.toDomain()
    }

    suspend fun getTriviaQuestions(): List<TriviaQuestion> = withContext(Dispatchers.IO) {
        TriviaData.allQuestions
    }

    // ---- Mappers ----
    private fun entitiesToGroupStandings(entities: List<StandingEntity>): List<GroupStanding> {
        return entities.groupBy { it.group }.map { (group, teams) ->
            GroupStanding(group, teams.sortedBy { it.position }.map {
                TeamStanding(it.teamName, it.teamCode, it.played, it.won, it.drawn, it.lost,
                    it.goalsFor, it.goalsAgainst, it.goalDifference, it.points, it.position)
            })
        }.sortedBy { it.group }
    }
}

// Extension mappers
fun TeamEntity.toDomain() = Team(code, name, code, group, confederation, flagEmoji, flagUrl, fifaRanking, coach, nickname, worldCupAppearances, bestFinish)
fun MatchEntity.toDomain() = Match(id, matchNumber, round, groupName, homeTeam, awayTeam, homeTeamCode, awayTeamCode, stadium, city, kickoffUtc, status, homeScore, awayScore)
fun VenueEntity.toDomain() = Venue(id, name, city, country, capacity, imageUrl, latitude, longitude)
