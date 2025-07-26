//
//  HeroDto.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

struct HeroDto: Decodable, Identifiable {
    let id: String // ← es String en el JSON
    let name: String
    let image: HeroImage
    let stats: HeroStatsDTO
    let biography: HeroBiography

    enum CodingKeys: String, CodingKey {
        case id
        case name
        case image
        case stats = "powerstats"
        case biography
    }

    func toDomain() -> Hero {
        Hero(
            id: Int(id) ?? 0,
            name: name,
            fullName: biography.fullName,
            image: image.url,
            stats: stats.toDomain()
        )
    }
}

struct HeroImage: Decodable {
    let url: String
}

struct HeroBiography: Decodable {
    let fullName: String

    enum CodingKeys: String, CodingKey {
        case fullName = "full-name"
    }
}

struct HeroStatsDTO: Decodable {
    let intelligence: String
    let strength: String
    let speed: String
    let durability: String
    let power: String
    let combat: String

    func toDomain() -> HeroStats {
        HeroStats(
            intelligence: intelligence,
            strength: strength,
            speed: speed,
            durability: durability,
            power: power,
            combat: combat
        )
    }
}
