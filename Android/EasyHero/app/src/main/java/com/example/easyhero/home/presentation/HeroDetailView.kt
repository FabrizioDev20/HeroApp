package com.example.easyhero.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.easyhero.home.Hero

@Composable
fun HeroDetailView(
    hero: Hero,
    onToggle: () -> Unit = {},
    viewModel: HeroCardViewModel = remember { PresentationModule.getHeroCardViewModel() }
) {
    val isFavorite by viewModel.isFavorite.collectAsState()

    LaunchedEffect(hero.id) {
        viewModel.checkFavorite(hero.id)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(240.dp)
        ) {
            AsyncImage(
                model = hero.imageUrl,
                contentDescription = hero.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp))
            )

            Box(
                modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).size(40.dp).clip(CircleShape).background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        viewModel.toggleFavorite(hero)
                        onToggle()
                    },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = hero.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        hero.realName?.let {
            Text(
                text = "Nombre completo: $it",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Power Stats", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Inteligencia: ${hero.powerstats.intelligence}")
            Text("Fuerza: ${hero.powerstats.strength}")
            Text("Velocidad: ${hero.powerstats.speed}")
            Text("Durabilidad: ${hero.powerstats.durability}")
            Text("Poder: ${hero.powerstats.power}")
            Text("Combate: ${hero.powerstats.combat}")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}