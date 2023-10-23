package com.skanderjabouzi.nbacompose.di

import android.annotation.SuppressLint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module {
    single<CoroutineDispatcher> {
        return@single provideDefaultDispatcher()
    }
    single<CoroutineDispatcher> {
        return@single provideMainDispatcher()
    }
    single<CoroutineDispatcher> {
        return@single provideIODispatcher()
    }
}

private fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

@SuppressLint("RawDispatchersUse")
private fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

private fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher
