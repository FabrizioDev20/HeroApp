package com.example.easyhero.favorites

import com.example.easyhero.favorites.data.FavoritesDataModule
import com.example.easyhero.favorites.presentation.FavoritesViewModel

object FavoritesModule {
    fun getFavoriteProductViewModel(): FavoritesViewModel {
        return FavoritesViewModel( favoriteHeroRepository = FavoritesDataModule.getFavoriteHeroRepository())
    }
}