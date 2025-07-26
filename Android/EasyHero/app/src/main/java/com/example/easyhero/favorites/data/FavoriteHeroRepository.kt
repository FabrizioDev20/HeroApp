package com.example.easyhero.favorites.data

import com.example.easyhero.favorites.FavoriteHero

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteHeroRepository (private val favoriteHeroDao: FavoriteHeroDao) {
    suspend fun insertFavoriteHero(favoriteHero: FavoriteHero)  = withContext(Dispatchers.IO){
        favoriteHeroDao.insertFavorite(FavoriteHeroDto(
            id = favoriteHero.id,
            name = favoriteHero.name,
            image = favoriteHero.image,
        ));
    }
    suspend fun deleteFavoriteHero(favoriteHero: FavoriteHero)  = withContext(Dispatchers.IO){
        favoriteHeroDao.deleteFavorite(FavoriteHeroDto(
            id = favoriteHero.id,
            name = favoriteHero.name,
            image = favoriteHero.image,
        ));
    }

    suspend fun fetchAllFavoriteHeroes(): List<FavoriteHero> = withContext(Dispatchers.IO) {
        favoriteHeroDao.fetchAllFavorites().map { favoriteHeroDto ->
            FavoriteHero(
                id = favoriteHeroDto.id,
                name = favoriteHeroDto.name,
                image = favoriteHeroDto.image,
            )
        }
    }

    suspend fun isHeroFavorite(id: Int): Boolean = withContext(Dispatchers.IO) {
        favoriteHeroDao.isFavorite(id)
    }

}