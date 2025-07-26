//
//  HeroService.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import Foundation

class HeroService {
    private let url = "https://www.superheroapi.com/api.php/f274286a22873ec9fc7a5782940f7ca2/search/"
    
    func getHeroes(query: String, completion: @escaping ([Hero]?, String?) -> Void) {
        let fullUrl = "\(url)\(query)"
        
        HttpRequestHelper().GET(url: fullUrl) { data, error in
            guard error == nil else {
                completion(nil, error)
                return
            }
            
            guard let data = data else {
                completion(nil, "No data received")
                return
            }
            
            do {
                if let json = try JSONSerialization.jsonObject(with: data) as? [String: Any],
                   let results = json["results"] {
                    
                    let resultsData = try JSONSerialization.data(withJSONObject: results)
                    
                    let heroes = try JSONDecoder().decode([HeroDto].self, from: resultsData).map {
                        $0.toDomain()
                    }
                    completion(heroes, nil)
                } 
            } catch {
                completion(nil, "Decoding error: \(error.localizedDescription)")
            }
        }
    }
}
