package com.skanderjabouzi.nbacompose.di

import com.skanderjabouzi.nbacompose.players.data.TeamPlayersRepository
import com.skanderjabouzi.nbacompose.players.domain.ITeamPlayersRepository
import com.skanderjabouzi.nbacompose.team.data.TeamDetailsRepository
import com.skanderjabouzi.nbacompose.team.domain.ITeamDetailsRepository
import com.skanderjabouzi.nbacompose.teams.data.TeamsRepository
import com.skanderjabouzi.nbacompose.teams.domain.ITeamsRepository
import org.koin.dsl.module

val repoModule = module {
    single<ITeamDetailsRepository> {
        TeamDetailsRepository(get(), get(), get())
    }
    single<ITeamPlayersRepository> {
        TeamPlayersRepository(get(), get(), get())
    }
    single<ITeamsRepository> {
        TeamsRepository(get(), get(), get())
    }
}
