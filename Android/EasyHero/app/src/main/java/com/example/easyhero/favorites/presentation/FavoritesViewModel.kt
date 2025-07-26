package com.example.easyhero.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.easyhero.favorites.FavoriteHero
import com.example.easyhero.favorites.data.FavoriteHeroRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel (private val favoriteHeroRepository : FavoriteHeroRepository) : ViewModel() {
    private val _favoriteHeroes = MutableStateFlow<List<FavoriteHero>>(emptyList())
    val favoriteHeroes : StateFlow<List<FavoriteHero>> get() = _favoriteHeroes

    fun getFavoriteHeroes() {
        viewModelScope.launch {
            _favoriteHeroes.value = favoriteHeroRepository.fetchAllFavoriteHeroes()
        }
    }

    fun removeFavoriteHero(favoriteHero : FavoriteHero) {
        viewModelScope.launch {
            favoriteHeroRepository.deleteFavoriteHero(favoriteHero)
            getFavoriteHeroes()
        }
    }
}