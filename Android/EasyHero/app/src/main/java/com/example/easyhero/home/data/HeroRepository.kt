package com.example.easyhero.home.data

import com.example.easyhero.home.Hero

class HeroRepository(private val service: HeroService) {

    suspend fun searchHeroes(name: String): List<Hero> {
        val response = service.searchHero(name)
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.response == "success" && body.heroes != null) {
                return body.heroes.map { it.toDomain() }
            }
        }
        return emptyList()
    }
}