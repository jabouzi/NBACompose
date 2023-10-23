package com.skanderjabouzi.nbacompose.di

import com.skanderjabouzi.nbacompose.players.presentation.TeamPlayersViewModel
import com.skanderjabouzi.nbacompose.team.presentation.TeamDetailsViewModel
import com.skanderjabouzi.nbacompose.teams.presentation.TeamsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        TeamPlayersViewModel(get(), get())
    }
    viewModel {
        TeamDetailsViewModel(get(), get())
    }
    viewModel {
        TeamsListViewModel(get())
    }
}
