package com.pawelsobaszek.rickandmortycharacters.model

import io.reactivex.Single
import retrofit2.http.GET

interface CharactersApi {

    @GET("?page=1")
    fun getCharacters(): Single<CharacterList>
}