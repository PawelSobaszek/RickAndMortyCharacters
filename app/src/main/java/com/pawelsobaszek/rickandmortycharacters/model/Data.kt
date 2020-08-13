package com.pawelsobaszek.rickandmortycharacters.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CharacterList(
    val results: List<Character>
)

data class Character(
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("origin")
    val origin: Objects?,
    @SerializedName("location")
    val location: Objects?
)

data class APIpages(
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)