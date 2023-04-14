package com.skanderjabouzi.nbacompose.hilt

import com.skanderjabouzi.nbacompose.players.data.ITeamPlayersRepository
import com.skanderjabouzi.nbacompose.players.data.TeamPlayersRepository
import com.skanderjabouzi.nbacompose.team.data.TeamDetailsRepository
import com.skanderjabouzi.nbacompose.team.domain.ITeamDetailsRepository
import com.skanderjabouzi.nbacompose.teams.data.ITeamsRepository
import com.skanderjabouzi.nbacompose.teams.data.TeamsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindTeamDetailsRepository(
        teamDetailsRepository: ITeamDetailsRepository
    ): TeamDetailsRepository

    @Binds
    abstract fun bindTeamsRepository(
        teamsRepository: ITeamsRepository
    ): TeamsRepository

    @Binds
    abstract fun bindTeamPlayersRepository(
        teamPlayersRepository: ITeamPlayersRepository
    ): TeamPlayersRepository
}