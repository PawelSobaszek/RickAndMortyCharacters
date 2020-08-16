package com.pawelsobaszek.rickandmortycharacters.model

import com.pawelsobaszek.rickandmortycharacters.model.Character

interface CharacterClickListener {
    fun onCharacterClick(character: Character)
}