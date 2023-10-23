package com.skanderjabouzi.nbacompose.di

import android.content.Context
import androidx.room.Room
import com.skanderjabouzi.nbacompose.database.PlayersDao
import com.skanderjabouzi.nbacompose.database.TeamDao
import com.skanderjabouzi.nbacompose.database.TeamDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { providePlayerDao(get()) }
    single { provideTeamDao(get()) }
}

fun provideTeamDao(database: TeamDatabase): TeamDao {
    return database.teamDao()
}

fun providePlayerDao(database: TeamDatabase): PlayersDao {
    return database.playersDao()
}

fun provideDatabase(appContext: Context): TeamDatabase {
    return Room.databaseBuilder(
        appContext,
        TeamDatabase::class.java,
        TeamDatabase.DATABASE_NAME
    ).build()
}
