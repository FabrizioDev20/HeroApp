//
//  HeroCardView.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import SwiftUI

struct HeroCardView: View {
    let hero: Hero
    
    @StateObject var viewModel = HeroCardViewModel()
        
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: hero.image)) { image in
                image
                    .resizable()
                    .scaledToFill()
                    .frame(height: 150)
                    .clipped()
            } placeholder: {
                ProgressView()
                    .frame(height: 150)
            }

            Text(hero.name)
                .lineLimit(1)
                .font(.headline)
                .bold()
            
            HStack {
                Button {
                    viewModel.toggleFavorite(hero: hero)
                } label: {
                    Image(systemName: viewModel.isFavorite ? "heart.fill" : "heart")
                        .resizable()
                        .frame(width: 24, height: 24)
                        .foregroundColor(.red)
                }
            }
        }
        .padding()
        .background(Color.white)
        .cornerRadius(12)
        .shadow(color: .gray.opacity(0.3), radius: 4, x: 0, y: 2)
        .onAppear {
            viewModel.checkFavorite(id: hero.id)
        }
    }
}
