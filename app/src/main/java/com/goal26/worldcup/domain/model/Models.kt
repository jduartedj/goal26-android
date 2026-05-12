package com.goal26.worldcup.domain.model

data class Team(
    val id: String,
    val name: String,
    val code: String,
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

data class Match(
    val id: Int,
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

data class GroupStanding(
    val group: String,
    val teams: List<TeamStanding>
)

data class TeamStanding(
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

data class Venue(
    val id: String,
    val name: String,
    val city: String,
    val country: String,
    val capacity: Int,
    val imageUrl: String?,
    val latitude: Double,
    val longitude: Double
)

data class TriviaQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val category: String,
    val difficulty: String,
    val explanation: String
)
