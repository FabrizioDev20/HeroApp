//
//  UIState.swift
//  EasyHero
//
//  Created by Alumno on 12/07/25.
//

enum UIState<T: Equatable> : Equatable {
    case idle
    case loading
    case success(T)
    case failure(String)
}
