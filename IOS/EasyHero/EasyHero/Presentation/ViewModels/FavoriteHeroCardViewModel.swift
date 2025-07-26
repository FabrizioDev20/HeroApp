//
//  FavoriteHeroCardViewModel.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import Foundation

class FavoriteHeroCardViewModel: ObservableObject {
    let dao = FavoriteHeroDao.shared
    
    func removeFavorite(id: Int) {
        dao.deleteFavorite(id: id)
    }
}
