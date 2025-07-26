package com.example.easyhero.home.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HomeDataModule {
    private const val TOKEN = "f274286a22873ec9fc7a5782940f7ca2"
    private const val BASE_URL = "https://superheroapi.com/api/$TOKEN/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val heroService = retrofit.create(HeroService::class.java)

    fun getHeroRepository(): HeroRepository {
        return HeroRepository(heroService)
    }
}