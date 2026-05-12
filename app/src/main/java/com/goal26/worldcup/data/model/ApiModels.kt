package com.goal26.worldcup.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMatch(
    @Json(name = "id") val id: Int,
    @Json(name = "match_number") val matchNumber: Int,
    @Json(name = "round") val round: String,
    @Json(name = "group_name") val groupName: String?,
    @Json(name = "home_team") val homeTeam: String,
    @Json(name = "away_team") val awayTeam: String,
    @Json(name = "stadium") val stadium: String,
    @Json(name = "kickoff_utc") val kickoffUtc: String,
    @Json(name = "status") val status: String,
    @Json(name = "home_score") val homeScore: Int? = null,
    @Json(name = "away_score") val awayScore: Int? = null
)

@JsonClass(generateAdapter = true)
data class ApiGroupStanding(
    @Json(name = "group") val group: String,
    @Json(name = "teams") val teams: List<ApiTeamStanding>
)

@JsonClass(generateAdapter = true)
data class ApiTeamStanding(
    @Json(name = "team") val team: String,
    @Json(name = "played") val played: Int,
    @Json(name = "won") val won: Int,
    @Json(name = "drawn") val drawn: Int,
    @Json(name = "lost") val lost: Int,
    @Json(name = "goals_for") val goalsFor: Int,
    @Json(name = "goals_against") val goalsAgainst: Int,
    @Json(name = "goal_difference") val goalDifference: Int,
    @Json(name = "points") val points: Int,
    @Json(name = "position") val position: Int
)

@JsonClass(generateAdapter = true)
data class ApiTeam(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "code") val code: String,
    @Json(name = "group") val group: String
)

@JsonClass(generateAdapter = true)
data class ApiStadium(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "city") val city: String,
    @Json(name = "country") val country: String,
    @Json(name = "capacity") val capacity: Int? = null
)
