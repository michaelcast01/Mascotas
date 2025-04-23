package com.example.carnet.IdentificacionMascotas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.carnet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenB(
    name: String,
    breed: String,
    size: String,
    age: Int,
    photoUrl: String,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Carnet Mascota") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(photoUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        error       = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Foto mascota",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Text("Nombre: $name", style = MaterialTheme.typography.titleMedium)
                    Text("Raza: $breed",  style = MaterialTheme.typography.bodyLarge)
                    Text("Tamaño: $size", style = MaterialTheme.typography.bodyLarge)
                    Text("Edad: $age años", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Button(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(48.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Volver")
            }
        }
    }
}
