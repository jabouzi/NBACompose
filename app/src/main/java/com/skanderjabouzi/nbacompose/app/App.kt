package com.skanderjabouzi.nbacompose.app

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.skanderjabouzi.nbacompose.di.appModule
import com.skanderjabouzi.nbacompose.di.databaseModule
import com.skanderjabouzi.nbacompose.di.dispatchersModule
import com.skanderjabouzi.nbacompose.di.repoModule
import com.skanderjabouzi.nbacompose.di.useCaseModule
import com.skanderjabouzi.nbacompose.di.viewModelModule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(appModule, repoModule, viewModelModule, databaseModule, dispatchersModule, useCaseModule))
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .build()
            }.build()
    }
}
