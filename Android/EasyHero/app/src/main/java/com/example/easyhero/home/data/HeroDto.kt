package com.example.easyhero.home.data

import com.example.easyhero.home.Hero
import com.example.easyhero.home.Powerstats
import com.google.gson.annotations.SerializedName

data class HeroDto(
    val id : Int,
    @SerializedName("name") val name: String,
    @SerializedName("biography") val biography: BiographyDto,
    @SerializedName("image") val image: ImageDto,
    @SerializedName("powerstats") val powerstats: PowerstatsDto
) {
    fun toDomain(): Hero {
        return Hero(
            id = id,
            name = name,
            realName = biography.fullName,
            imageUrl = image.url,
            powerstats = Powerstats(
                intelligence = powerstats.intelligence,
                strength = powerstats.strength,
                speed = powerstats.speed,
                durability = powerstats.durability,
                power = powerstats.power,
                combat = powerstats.combat
            )
        )
    }
}

data class BiographyDto(
    @SerializedName("full-name") val fullName: String
)

data class ImageDto(
    @SerializedName("url") val url: String
)

data class PowerstatsDto(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class HeroesDto(
    @SerializedName("response") val response: String,
    @SerializedName("results") val heroes: List<HeroDto>? = null,
    @SerializedName("error") val error: String? = null
)