//
//  HeroDetailView.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import SwiftUI

struct HeroDetailView: View {
    let hero: Hero
    let onToggle: () -> Void

    @StateObject var viewModel = HeroDetailViewModel()

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                ZStack(alignment: .topTrailing) {
                    AsyncImage(url: URL(string: hero.image)) { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(height: 240)
                            .frame(maxWidth: .infinity)
                            .clipped()
                    } placeholder: {
                        ProgressView()
                            .frame(height: 240)
                            .frame(maxWidth: .infinity)
                    }

                    Button {
                        viewModel.toggleFavorite(hero: hero)
                        onToggle()
                    } label: {
                        Image(systemName: viewModel.isFavorite ? "heart.fill" : "heart")
                            .resizable()
                            .frame(width: 24, height: 24)
                            .foregroundColor(.red)
                            .padding()
                    }
                }

                VStack(alignment: .leading, spacing: 4) {
                    Text(hero.name)
                        .font(.title)
                        .bold()

                    Text("Nombre real: \(hero.fullName)")
                        .font(.subheadline)
                        .foregroundColor(.gray)
                }

                VStack(alignment: .leading, spacing: 8) {
                    Text("Estadísticas")
                        .font(.headline)
                        .bold()

                    Text("🧠 Inteligencia: \(hero.stats.intelligence)")
                    Text("💪 Fuerza: \(hero.stats.strength)")
                    Text("⚡️ Velocidad: \(hero.stats.speed)")
                    Text("🛡 Durabilidad: \(hero.stats.durability)")
                    Text("🔋 Poder: \(hero.stats.power)")
                    Text("🥋 Combate: \(hero.stats.combat)")
                }
                .font(.subheadline)

                Spacer()
            }
            .padding()
        }
        .onAppear {
            viewModel.checkFavorite(id: hero.id)
        }
        .navigationTitle(hero.name)
        .navigationBarTitleDisplayMode(.inline)
    }
}
