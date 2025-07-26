package com.example.easyhero.favorites.data

import androidx.room.Room
import com.example.easyhero.EasyHeroApplication

object FavoritesDataModule {
    fun getFavoriteHeroRepository(): FavoriteHeroRepository {
        return FavoriteHeroRepository(favoriteHeroDao = getFavoriteHeroDao())
    }

    fun getFavoriteHeroDao(): FavoriteHeroDao {
        return getAppDatabase().getFavoriteHeroDao()
    }

    fun getAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            EasyHeroApplication.instance.applicationContext,
            AppDatabase::class.java,
            "easyhero"
        ).build()
    }
}