//
//  Hero.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

struct Hero: Identifiable, Equatable {
    let id: Int
    let name: String
    let fullName: String
    let image: String
    let stats: HeroStats
}

struct HeroStats: Equatable {
    let intelligence: String
    let strength: String
    let speed: String
    let durability: String
    let power: String
    let combat: String
}
