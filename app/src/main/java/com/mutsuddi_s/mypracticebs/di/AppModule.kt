package com.mutsuddi_s.mypracticebs.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mutsuddi_s.mypracticebs.data.remote.CharacterService
import com.mutsuddi_s.mypracticebs.utils.Constants.BASE_URL
import com.mutsuddi_s.mypracticebs.utils.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
   /* @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(
        CharacterService::class.java)*/

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): CharacterService{
        return retrofit.create(CharacterService::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNetworkChecker(@ApplicationContext context: Context): NetworkChecker {
        return NetworkChecker(context)
    }
}