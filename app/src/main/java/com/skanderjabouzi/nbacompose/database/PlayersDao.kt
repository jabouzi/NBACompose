package com.skanderjabouzi.nbacompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skanderjabouzi.nbacompose.models.db.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(players: PlayerEntity)

    @Query("SELECT * FROM playerentity WHERE teamId = :id")
    fun getPlayers(id: Int): Flow<List<PlayerEntity>>

    @Query("SELECT count(*) FROM playerentity WHERE id = :id")
    fun getPlayerExists(id: Int): Int
}