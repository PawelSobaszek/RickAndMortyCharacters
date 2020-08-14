package com.pawelsobaszek.rickandmortycharacters.view

import com.pawelsobaszek.rickandmortycharacters.model.Character

interface CharacterClickListener {
    fun onCharacterClick(character: Character)
}