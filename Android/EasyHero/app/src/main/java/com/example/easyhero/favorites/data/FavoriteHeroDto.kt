package com.example.easyhero.favorites.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteHeroDto(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
)