//
//  HomeViewModel.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import Foundation

class HomeViewModel: ObservableObject {
    @Published var state: UIState<[Hero]> = .idle
    
    private let heroService = HeroService()
    
    init() {
        getHeroes(query: "")
    }
    
    func getHeroes(query: String) {
        self.state = .loading
        
        heroService.getHeroes(query: query) { [weak self] data, message in
            DispatchQueue.main.async {
                if let data = data {
                    self?.state = .success(data)
                } else {
                    self?.state = .failure(message ?? "Unknown error")
                }
            }
        }
    }
}
