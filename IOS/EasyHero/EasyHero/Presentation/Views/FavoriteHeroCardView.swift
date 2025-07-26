//
//  FavoriteHeroCardView.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

import SwiftUI

struct FavoriteHeroCardView: View {
    @State private var showOptions = false
    
    @StateObject var viewModel = FavoriteHeroCardViewModel()
    
    let favorite: FavoriteHero
    let onDelete: () -> Void

    var body: some View {
        HStack(spacing: 16) {
            AsyncImage(url: URL(string: favorite.image)) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                        .frame(width: 100, height: 100)
                case .success(let image):
                    image.resizable()
                        .scaledToFill()
                        .frame(width: 100, height: 100)
                        .clipShape(RoundedRectangle(cornerRadius: 8))
                case .failure:
                    Color.gray
                        .frame(width: 100, height: 100)
                @unknown default:
                    EmptyView()
                }
            }

            VStack(alignment: .leading, spacing: 6) {
                Text(favorite.name)
                    .font(.headline)
                    .lineLimit(1)
            }

            Spacer()

            Button {
                showOptions = true
            } label: {
                Image(systemName: "ellipsis")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 24, height: 24)
                    .rotationEffect(.degrees(90))
            }
            .buttonStyle(.borderless)
            .foregroundColor(.black)
        }
        .padding()
        .background(Color.gray.opacity(0.1))
        .clipShape(RoundedRectangle(cornerRadius: 12))
        .sheet(isPresented: $showOptions) {
            VStack(alignment: .leading, spacing: 20) {
                Text("Opciones")
                    .font(.headline)
                Button {
                    viewModel.removeFavorite(id: favorite.id)
                    showOptions = false
                    onDelete()
                } label: {
                    Label("Eliminar de favoritos", systemImage: "trash")
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .foregroundStyle(.red)
                }

                Spacer()
            }
            .padding()
            .presentationDetents([.height(150)])
        }
    }
}
