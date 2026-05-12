package com.goal26.worldcup.data.api

import com.goal26.worldcup.data.model.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Wc2026ApiService {
    @GET("/matches")
    suspend fun getMatches(
        @Header("Authorization") auth: String,
        @Query("team") team: String? = null,
        @Query("group") group: String? = null,
        @Query("round") round: String? = null,
        @Query("status") status: String? = null
    ): List<ApiMatch>

    @GET("/standings")
    suspend fun getStandings(
        @Header("Authorization") auth: String,
        @Query("group") group: String? = null
    ): List<ApiGroupStanding>

    @GET("/teams")
    suspend fun getTeams(
        @Header("Authorization") auth: String
    ): List<ApiTeam>

    @GET("/stadiums")
    suspend fun getStadiums(
        @Header("Authorization") auth: String
    ): List<ApiStadium>
}
