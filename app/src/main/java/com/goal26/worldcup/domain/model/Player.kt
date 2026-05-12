package com.goal26.worldcup.domain.model

data class Player(
    val name: String,
    val number: Int,
    val position: String,
    val club: String,
    val age: Int,
    val caps: Int,
    val goals: Int,
    val isCaptain: Boolean = false
)

data class TeamSquad(
    val teamCode: String,
    val players: List<Player>
)
