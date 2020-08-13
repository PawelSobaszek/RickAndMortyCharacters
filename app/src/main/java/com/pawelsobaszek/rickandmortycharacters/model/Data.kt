package com.pawelsobaszek.rickandmortycharacters.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Character(
    @SerializedName("name")
    val characterName: String?,
    @SerializedName("image")
    val characterImage: String?
)

data class CharacterDetail(
    @SerializedName("cd_name")
    val name: String?,
    @SerializedName("cd_photo")
    val image: String?,
    @SerializedName("cd_gender")
    val gender: String?,
    @SerializedName("cd_status")
    val status: String?,
    @SerializedName("cd_origin")
    val origin: Objects?,
    @SerializedName("cd_location")
    val location: Objects?
)