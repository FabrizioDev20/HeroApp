package com.example.easyhero.home

data class Hero(
    val id : Int,
    val name: String,
    val realName: String,
    val imageUrl: String,
    val powerstats: Powerstats
)

data class Powerstats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)