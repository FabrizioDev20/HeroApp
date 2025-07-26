package com.example.easyhero.favorites.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.easyhero.favorites.FavoriteHero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesView(viewModel: FavoritesViewModel) {
    val favorites = viewModel.favoriteHeroes.collectAsState()
    var showOptions = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val selectedFavorite = remember { mutableStateOf<FavoriteHero?>(null) }

    viewModel.getFavoriteHeroes()

    Scaffold { padding ->
        LazyColumn (modifier = Modifier.padding()) {
            items(favorites.value) { favorite ->
                FavoriteCardView (favorite = favorite) {
                    showOptions.value = true
                    selectedFavorite.value = favorite
                }
            }
        }

        if (showOptions.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    showOptions.value = false
                },
                sheetState = sheetState
            ) {
                Column {
                    Text("Options",
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Row (modifier = Modifier.padding(8.dp).clickable{
                        selectedFavorite.value?.let { hero ->
                            viewModel.removeFavoriteHero(hero)
                        }
                        showOptions.value = false
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                        Text("Remove from favorites")
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}