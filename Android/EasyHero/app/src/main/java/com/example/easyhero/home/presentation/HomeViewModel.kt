package com.example.easyhero.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyhero.home.Hero
import com.example.easyhero.home.data.HeroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HeroRepository) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _heroes = MutableStateFlow<List<Hero>>(emptyList())
    val heroes: StateFlow<List<Hero>> = _heroes

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun searchHeroes() {
        viewModelScope.launch {
            try {
                val result = repository.searchHeroes(_query.value)
                _heroes.value = result
            } catch (e: Exception) {
                _heroes.value = emptyList()
            }
        }
    }
}