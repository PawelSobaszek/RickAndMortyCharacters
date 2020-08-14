package com.pawelsobaszek.rickandmortycharacters.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("?")
    fun getCharacters(@Query("page") page: String): Single<CharacterList>
}