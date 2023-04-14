package com.skanderjabouzi.nbacompose.hilt

import android.content.Context
import androidx.room.Room
import com.skanderjabouzi.nbacompose.database.PlayersDao
import com.skanderjabouzi.nbacompose.database.TeamDao
import com.skanderjabouzi.nbacompose.database.TeamDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideTeamDao(database: TeamDatabase): TeamDao {
        return database.teamDao()
    }

    @Provides
    fun providePlayerDao(database: TeamDatabase): PlayersDao {
        return database.playersDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TeamDatabase {
        return Room.databaseBuilder(
            appContext,
            TeamDatabase::class.java,
            TeamDatabase.DATABASE_NAME
        ).build()
    }
}