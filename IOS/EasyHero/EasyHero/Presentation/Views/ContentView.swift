//
//  ContentView.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationStack {
            TabView {
                Tab("Home", systemImage: "theatermasks") {
                    HomeView()
                }
                
                Tab("Favorites", systemImage: "heart") {
                    FavoritesView()
                }
            }
            .tint(Color.primary)
        }
    }
}

#Preview {
    ContentView()
}
