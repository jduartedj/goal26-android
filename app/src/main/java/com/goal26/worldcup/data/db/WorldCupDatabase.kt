package com.goal26.worldcup.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goal26.worldcup.data.db.entity.*

@Database(
    entities = [
        TeamEntity::class,
        MatchEntity::class,
        StandingEntity::class,
        VenueEntity::class,
        CacheMetaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WorldCupDatabase : RoomDatabase() {
    abstract fun worldCupDao(): WorldCupDao
}
