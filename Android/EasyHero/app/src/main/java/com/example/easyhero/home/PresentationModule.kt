package com.example.easyhero.home.presentation

import com.example.easyhero.favorites.data.FavoritesDataModule
import com.example.easyhero.favorites.presentation.FavoritesViewModel
import com.example.easyhero.home.data.HomeDataModule

object PresentationModule {
    fun getHomeViewModel(): HomeViewModel {
        val repository = HomeDataModule.getHeroRepository()
        return HomeViewModel(repository)
    }

    fun getHeroCardViewModel(): HeroCardViewModel {
        val favoriteRepository = FavoritesDataModule.getFavoriteHeroRepository()
        return HeroCardViewModel(favoriteRepository)
    }

    fun getFavoritesViewModel(): FavoritesViewModel {
        val favoriteRepository = FavoritesDataModule.getFavoriteHeroRepository()
        return FavoritesViewModel(favoriteRepository)
    }
}