package com.goal26.worldcup.data.db

import androidx.room.*
import com.goal26.worldcup.data.db.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WorldCupDao {

    // Teams
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamEntity>)

    @Query("SELECT * FROM teams ORDER BY `group`, fifaRanking")
    fun getAllTeams(): Flow<List<TeamEntity>>

    @Query("SELECT * FROM teams WHERE `group` = :group")
    fun getTeamsByGroup(group: String): Flow<List<TeamEntity>>

    @Query("SELECT * FROM teams WHERE code = :code")
    suspend fun getTeamByCode(code: String): TeamEntity?

    @Query("SELECT * FROM teams WHERE name LIKE '%' || :query || '%' OR nickname LIKE '%' || :query || '%'")
    fun searchTeams(query: String): Flow<List<TeamEntity>>

    // Matches
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches: List<MatchEntity>)

    @Query("SELECT * FROM matches ORDER BY kickoffUtc")
    fun getAllMatches(): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE groupName = :group ORDER BY kickoffUtc")
    fun getMatchesByGroup(group: String): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE id = :id")
    suspend fun getMatchById(id: Int): MatchEntity?

    @Query("SELECT * FROM matches WHERE status = 'scheduled' ORDER BY kickoffUtc LIMIT 1")
    suspend fun getNextMatch(): MatchEntity?

    // Standings
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStandings(standings: List<StandingEntity>)

    @Query("SELECT * FROM standings ORDER BY `group`, position")
    fun getAllStandings(): Flow<List<StandingEntity>>

    @Query("SELECT * FROM standings WHERE `group` = :group ORDER BY position")
    fun getStandingsByGroup(group: String): Flow<List<StandingEntity>>

    // Venues
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenues(venues: List<VenueEntity>)

    @Query("SELECT * FROM venues ORDER BY name")
    fun getAllVenues(): Flow<List<VenueEntity>>

    // Cache metadata
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCacheMeta(meta: CacheMetaEntity)

    @Query("SELECT * FROM cache_meta WHERE dataKey = :key")
    suspend fun getCacheMeta(key: String): CacheMetaEntity?

    @Query("DELETE FROM cache_meta WHERE dataKey = :key")
    suspend fun deleteCacheMeta(key: String)
}
