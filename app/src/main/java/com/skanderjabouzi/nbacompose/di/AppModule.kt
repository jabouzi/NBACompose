package com.skanderjabouzi.nbacompose.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.skanderjabouzi.nbacompose.BuildConfig
import com.skanderjabouzi.nbacompose.helpers.NetworkHelper
import com.skanderjabouzi.nbacompose.network.MockInterceptor
import com.skanderjabouzi.nbacompose.network.RetrofitClient
import com.skanderjabouzi.nbacompose.network.TeamsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideGson() }
    single { provideOkHttp(androidContext()) }
    single { provideRetrofit(get(), get()) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single { provideRetrofitClient(get()) }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideGson(): GsonConverterFactory {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return GsonConverterFactory.create(gson)
}

private fun provideOkHttp(context: Context): OkHttpClient {
    val okhttpClient = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    okhttpClient.addInterceptor(logging)
    if (BuildConfig.FLAVOR.equals("mock")) {
        okhttpClient.addInterceptor(MockInterceptor(context))
    }
    return okhttpClient.build()
}
private fun provideRetrofit(
    okhttpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory
): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonFactory)
        .client(okhttpClient)
        .build()

    return retrofit
}

private fun provideApiService(retrofit: Retrofit): TeamsApi = retrofit.create(TeamsApi::class.java)

private fun provideRetrofitClient(teamsApi: TeamsApi) = RetrofitClient(teamsApi)
