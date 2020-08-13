package com.pawelsobaszek.rickandmortycharacters.di

import com.pawelsobaszek.rickandmortycharacters.model.CharactersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://rickandmortyapi.com/api/character/"

    @Provides
    fun provideCharacterApi() : CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }
}