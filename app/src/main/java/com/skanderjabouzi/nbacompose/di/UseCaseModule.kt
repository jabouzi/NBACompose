package com.skanderjabouzi.nbacompose.di

import com.skanderjabouzi.nbacompose.players.domain.TeamPlayersUseCase
import com.skanderjabouzi.nbacompose.team.domain.TeamDetailsUseCase
import com.skanderjabouzi.nbacompose.teams.domain.TeamsListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { TeamDetailsUseCase(get()) }
    single { TeamPlayersUseCase(get()) }
    single { TeamsListUseCase(get()) }
}
