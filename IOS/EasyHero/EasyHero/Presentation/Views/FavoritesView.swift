//
//  FavoritesView.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import SwiftUI

struct FavoritesView: View {
    @StateObject var viewModel = FavoritesViewModel()
    
    var body: some View {
        VStack {
            if (!viewModel.favorites.isEmpty) {
                List {
                    ForEach(viewModel.favorites) { favorite in
                        FavoriteHeroCardView(favorite: favorite) {
                            viewModel.getAllFavorites()
                        }
                    }
                    .listRowSeparator(.hidden)
                }
                .listStyle(.plain)
            } else {
                Text("No favorites")
            }
           
        }
        .onAppear {
            viewModel.getAllFavorites()
        }
    }
}
