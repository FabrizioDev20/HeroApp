package com.example.easyhero.favorites.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteHeroDao {
    @Insert
    suspend fun insertFavorite(favorite: FavoriteHeroDto)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteHeroDto)

    @Query("SELECT * FROM favorites")
    fun fetchAllFavorites(): List<FavoriteHeroDto>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}