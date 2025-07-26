//
//  FavoriteHeroEntity+Extensions.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

extension FavoriteHeroEntity {
    func fromDomain(favorite: FavoriteHero) {
        self.id = Int16(favorite.id)
        self.name = favorite.name
        self.image = favorite.image
    }
    
    func toDomain() -> FavoriteHero {
        FavoriteHero(id: Int(id), name: name ?? "", image: image ?? "")
    }
}
