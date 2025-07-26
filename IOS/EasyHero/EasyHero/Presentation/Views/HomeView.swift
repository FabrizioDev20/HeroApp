//
//  HomeView.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import SwiftUI

struct HomeView: View {
    @State private var search = ""
    @StateObject private var viewModel = HomeViewModel()
    @State private var selectedHero: Hero? = nil

    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 16) {
                    HStack {
                        TextField("Buscar superhéroe", text: $search)
                            .autocorrectionDisabled()
                            .textInputAutocapitalization(.never)
                            .padding(.leading, 8)
                        Button {
                            viewModel.getHeroes(query: search)
                        } label: {
                            Image(systemName: "magnifyingglass")
                                .foregroundColor(.white)
                                .padding(8)
                                .background(Color.blue)
                                .clipShape(Circle())
                        }
                    }
                    .padding(8)
                    .background(Color.gray.opacity(0.1))
                    .cornerRadius(12)

                    switch viewModel.state {
                    case .idle, .loading:
                        ProgressView("Cargando héroes...")

                    case .success(let heroes):
                        LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())], spacing: 16) {
                            ForEach(heroes) { hero in
                                HeroCardView(hero: hero)
                                    .onTapGesture {
                                        selectedHero = hero
                                    }
                            }
                        }

                    case .failure(let message):
                        Text("Error: \(message)")
                            .foregroundColor(.red)
                    }
                }
                .padding()
            }
            .sheet(item: $selectedHero) { hero in
                HeroDetailView(hero: hero) {
                    viewModel.getHeroes(query: search)
                }
            }
        }
    }
}
