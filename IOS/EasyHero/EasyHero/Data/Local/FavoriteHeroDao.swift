//
//  Untitled.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import CoreData

class FavoriteHeroDao {
    static let shared = FavoriteHeroDao()
    
    private init () {}
    
    private let context = PersistenceController.shared.container.viewContext
    
    func insertFavorite(favorite: FavoriteHero){
        let entity = FavoriteHeroEntity(context: context)
        entity.fromDomain(favorite: favorite)
        saveContext()
    }
    
    func deleteFavorite(id: Int) {
        let request: NSFetchRequest<FavoriteHeroEntity>
        request = FavoriteHeroEntity.fetchRequest()
        request.predicate = NSPredicate(format: "id = %i", argumentArray: [id])
        
        do {
            let entities = try context.fetch(request)
            if let entity = entities.first {
                context.delete(entity)
                saveContext()
            }
        } catch let error {
            fatalError(error.localizedDescription)
        }
    }
    
    func fetchAllFavorites() -> [FavoriteHero] {
        let request: NSFetchRequest<FavoriteHeroEntity>
        request = FavoriteHeroEntity.fetchRequest()
        
        do {
            let entities = try context.fetch(request)
            return entities.map { $0.toDomain()}
          
        } catch let error {
            fatalError(error.localizedDescription)
        }
    }
    
    func checkFavorite(id: Int) -> Bool {
        let request: NSFetchRequest<FavoriteHeroEntity>
        request = FavoriteHeroEntity.fetchRequest()
        request.predicate = NSPredicate(format: "id = %i", argumentArray: [id])
        
        do {
            let entities = try context.fetch(request)
            print(!entities.isEmpty)
            return !entities.isEmpty
        } catch let error {
            fatalError(error.localizedDescription)
        }
    }
    
    
    private func saveContext() {
        if context.hasChanges {
            do {
                try context.save()
            } catch let error {
                fatalError(error.localizedDescription)
            }
        }
    }
}
