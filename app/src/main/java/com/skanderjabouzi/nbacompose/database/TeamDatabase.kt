package com.skanderjabouzi.nbacompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skanderjabouzi.nbacompose.models.db.PlayerEntity
import com.skanderjabouzi.nbacompose.models.db.TeamDetailsEntity
import com.skanderjabouzi.nbacompose.models.db.TeamEntity

@Database(
  entities = [TeamEntity::class, PlayerEntity::class, TeamDetailsEntity::class],
  version = 2,
  exportSchema = false
)

abstract class TeamDatabase : RoomDatabase() {

  companion object {
    const val DATABASE_NAME = "nba.db"
  }

  abstract fun teamDao(): TeamDao

  abstract fun playersDao(): PlayersDao

}