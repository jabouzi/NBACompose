package com.skanderjabouzi.nbacompose.hilt

import com.skanderjabouzi.nbacompose.players.domain.ITeamPlayersRepository
import com.skanderjabouzi.nbacompose.players.data.TeamPlayersRepository
import com.skanderjabouzi.nbacompose.team.data.TeamDetailsRepository
import com.skanderjabouzi.nbacompose.team.domain.ITeamDetailsRepository
import com.skanderjabouzi.nbacompose.teams.domain.ITeamsRepository
import com.skanderjabouzi.nbacompose.teams.data.TeamsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindTeamDetailsRepository(
        teamDetailsRepository: TeamDetailsRepository
    ): ITeamDetailsRepository

    @Binds
    abstract fun bindTeamsRepository(
        teamsRepository: TeamsRepository
    ): ITeamsRepository

    @Binds
    abstract fun bindTeamPlayersRepository(
        teamPlayersRepository: TeamPlayersRepository
    ): ITeamPlayersRepository
}