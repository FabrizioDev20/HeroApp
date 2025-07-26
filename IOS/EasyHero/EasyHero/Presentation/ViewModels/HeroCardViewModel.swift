//
//  HeroCardViewModel.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import Foundation

class HeroCardViewModel: ObservableObject {
    @Published var isFavorite: Bool = false
    private let dao = FavoriteHeroDao.shared
    
    
    func checkFavorite(id: Int) {
        isFavorite = dao.checkFavorite(id: id)
    }
    
    func toggleFavorite(hero: Hero) {
        isFavorite.toggle()
        
        if (isFavorite) {
            addFavorite(hero: hero)
        } else {
            removeFavorite(id: hero.id)
        }
    }
    
    private func removeFavorite(id: Int) {
        dao.deleteFavorite(id: id)
    }
    
    private func addFavorite(hero: Hero) {
        dao.insertFavorite(favorite: FavoriteHero(id: hero.id, name: hero.name, image: hero.image))
    }
    
}
