package com.example.easyhero.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyhero.favorites.FavoriteHero
import com.example.easyhero.favorites.data.FavoriteHeroRepository
import com.example.easyhero.home.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeroCardViewModel(private val favoriteHeroRepository: FavoriteHeroRepository) : ViewModel() {
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun checkFavorite(id: Int) {
        viewModelScope.launch {
            _isFavorite.value = favoriteHeroRepository.isHeroFavorite(id)
        }
    }

    fun toggleFavorite(hero: Hero) {
        viewModelScope.launch {
            val currentlyFavorite = favoriteHeroRepository.isHeroFavorite(hero.id)
            if (currentlyFavorite) {
                favoriteHeroRepository.deleteFavoriteHero(
                    FavoriteHero(hero.id, hero.name, hero.imageUrl)
                )
                _isFavorite.value = false
            } else {
                favoriteHeroRepository.insertFavoriteHero(
                    FavoriteHero(hero.id, hero.name, hero.imageUrl)
                )
                _isFavorite.value = true
            }
        }
    }
}