package com.example.easyhero.home.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {
    @GET("search/{name}")
    suspend fun searchHero(
        @Path("name") name: String
    ): Response<HeroesDto>
}