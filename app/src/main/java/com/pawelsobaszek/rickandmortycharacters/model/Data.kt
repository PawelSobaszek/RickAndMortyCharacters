package com.pawelsobaszek.rickandmortycharacters.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    val info: PageInfo,
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
    val origin: OriginInfo,
    val location: LocationInfo
)

data class PageInfo(
    @SerializedName("pages")
    val pages: Int?
)

data class OriginInfo(
    @SerializedName("name")
    val origin: String
)

data class LocationInfo(
    @SerializedName("name")
    val location: String
)

