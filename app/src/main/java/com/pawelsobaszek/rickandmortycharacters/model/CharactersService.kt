package com.pawelsobaszek.rickandmortycharacters.model

import com.pawelsobaszek.rickandmortycharacters.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject


class CharactersService {

    @Inject
    lateinit var api: CharactersApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCharacters(): Single<List<Character>> {
        return api.getCharacters()
    }
}