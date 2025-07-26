//
//  FavoritesViewModel.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import Foundation

class FavoritesViewModel: ObservableObject {
    @Published var favorites = [FavoriteHero]()
    private let dao = FavoriteHeroDao.shared
    
    func getAllFavorites() {
        favorites = dao.fetchAllFavorites()
    }
}
