package com.example.easyhero.favorites.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteHeroDto::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getFavoriteHeroDao(): FavoriteHeroDao
}