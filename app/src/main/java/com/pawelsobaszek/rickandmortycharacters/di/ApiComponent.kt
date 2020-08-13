package com.pawelsobaszek.rickandmortycharacters.di

import com.pawelsobaszek.rickandmortycharacters.model.CharactersService
import com.pawelsobaszek.rickandmortycharacters.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CharactersService)

    fun inject(viewModel: ListViewModel)
}